<template>
    <ListTable 
        :items="pageItems" 
        :fields="fields"
        @row-clicked="listClicked"
    />
    <ListPagination 
        :current-page="currentPage" 
        :total-pages="totalPages"
        @go-back="goBack"
        @go-next="goNext"
    />
</template>

<script setup>
    import { ref, onMounted, computed } from 'vue';
    import { useRouter } from 'vue-router';
    import ListTable from '@/components/body/gather/ListTable.vue';
    import ListPagination from '@/components/body/gather/ListPagination.vue';

    const router = useRouter();
    const items = ref(null);

    const index = ref(0);
    const next = ref(10);
    const pageItems = computed(() => items.value ? items.value.slice(index.value, next.value) : []);
    const totalItems = computed(() => items.value ? items.value.length : 0);
    const currentPage = computed(() => Math.floor(index.value / 10) + 1);
    const totalPages = computed(() => Math.ceil(totalItems.value / 10));

    const fetchGatherList = async () => {
        try {
            console.log('fetchGatherList 실행');
            const response = await fetch('http://localhost:8080/gatherlist');
        if (!response.ok) {
            throw new Error('정보 불러오기 실패');
        }
        items.value = await response.json();
        console.log(items);
        } catch (error) {
            console.error("데이터 로딩중 에러 발생: ", error);
        }
    };

    const fields = ref([
        { key: 'id', label: '' },
        { key: '모임리더', label: '모임 리더' },
        { key: '모임명', label: '모임 명' },
        { key: '생성일자', label: '생성 일자' },
        { key: '해당 이벤트', label: '해당 이벤트' }
    ]);

    const listClicked = (item, index, event) => {
        router.push(`/gatherdetail/${item.id}`);
    };

    const goBack = () => {
        if (index.value > 0) {
            index.value -= 10;
            next.value -= 10;
        }
    };

    const goNext = () => {
        if (next.value < totalItems.value) {
            index.value += 10;
            next.value += 10;
        }
    };

    onMounted(() => {
        fetchGatherList();
    });
</script>

<style scoped>
    .section {
        overflow: auto;
    }
    .container {
        display: flex;
        flex-direction: column;
        overflow: auto;
    }   
    .pagename {
        display: flex;
        padding-top: 2rem;
        color: #49D5FF;
        justify-content: space-between;
        font-family: 'Jua', sans-serif;
    }
    .gatherbtn {
        display: flex;
        justify-content: end;
        margin-right: 3rem;
        padding-bottom: 1rem;
        gap: 1rem;
    }
</style>