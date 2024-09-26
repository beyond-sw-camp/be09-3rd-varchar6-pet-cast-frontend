<template>
    <section class="section">
        <div class="container" id="content">
            <div class="pagename">
                <h1>모임 목록 조회</h1>
                <div class="gatherbtn">
                    <b-button size="lg" variant="outline-primary">나의 모임 조회</b-button>
                    <b-button size="lg" variant="outline-primary">모임 생성</b-button>
                </div>
            </div>
            <div class="gatherlist">
                    <b-table 
                    striped 
                    hover 
                    :items="items" 
                    :fields="fields"
                    @row-clicked="listClicked"
                    ></b-table>
            </div>
            <div class="paging">
                <b-button-toolbar key-nav aria-label="Toolbar with button groups">
                    <b-button-group class="before">
                        <b-button @click="prevPage" :disabled="currentPage === 1">&lsaquo;</b-button>
                        </b-button-group>
                    <b-button-group class="current">
                        <b-button>aaa</b-button>                
                    </b-button-group>
                <b-button-group class="after">
                    <b-button @click="nextPage" :disabled="currentPage === totalPages">&rsaquo;</b-button>
                    </b-button-group>
                </b-button-toolbar>
            </div>
        </div>
    </section>

</template>

<script setup>
    import { ref, onMounted } from 'vue';
    import { useRoute, useRouter } from 'vue-router';
    
    const currentRoute = useRoute();
    const router = useRouter();
    const items = ref(null);
    
    
    
    const fetchGatherList = async () => {
        try{
            console.log('fetchGatherList 실행');
            const response = await fetch('http://localhost:8080/gatherlist');
            if(!response.ok){
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
    }



    onMounted(() => {
        fetchGatherList();
    });
</script>

<style scoped>
    .section{
        overflow: auto;
        /* height: 100vh; */
    }
    .container{
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
    .gatherbtn{
        display: flex;
        justify-content: end;
        margin-right: 3rem;
        padding-bottom: 1rem;
        gap: 1rem;
    }
    .gatherlist {
        display: flex;
        justify-content: center;
        align-items: center;
        padding-left: 2rem;
        padding-right: 2rem; 
        flex-grow: 1; 
        overflow-y: auto;
    }
    .table {
    text-align: center;
    }
    .paging {
        display: flex;
        justify-content: center;
        align-items: center;
        padding-bottom: 6rem;
    }
</style>