<template>
    <section class="container">
        <DeleteTitle/>
        
        <DeleteInfo
            :gatherImg="gatherDetail?.gatherImg"
            :gatherName="gatherDetail?.gathername"
            />        
        <article class = "detail-info">
            <DeleteDescription :gatherDetail="gatherDetail"/>
            
            <DeleteBtn
                :gatherId="gatherDetail?.id"
                :show="show"/>
        </article>
    </section>
</template>

<script setup>
    import { ref, onMounted } from 'vue';
    import { useRoute } from 'vue-router'; 

    import DeleteTitle from '@/components/body/gather/delete/DeleteTitle.vue';
    import DeleteInfo from '@/components/body/gather/delete/DeleteInfo.vue';
    import DeleteDescription from '@/components/body/gather/delete/DeleteDescription.vue';
    import DeleteBtn from '@/components/body/gather/delete/DeleteBtn.vue';

    const currentRoute = useRoute();    
    const gatherList = ref([]);
    const gatherDetail = ref(null);

    const fetchGatherDetail = async () => {
        try{
            console.log('fetchGatherDetail 실행');
            const response = await fetch('http://localhost:8081/gatherdetail');
            if(!response.ok){
                throw new Error('정보 불러오기 실패');
            }
            // 통신해서 온 정보 포장 까기
            gatherList.value = await response.json();
            // 파라미터로 넘어온 id 값을 통해 해당 정보들 저장
            gatherDetail.value = gatherList.value[parseInt(currentRoute.params.id)-1];
        } catch (error) {
            console.error("데이터 로딩중 에러 발생", error);
        }
    }

    onMounted(() => {
        fetchGatherDetail();
    })
</script>

<style scoped>
    .container {
        overflow: auto;
        height: 100vh;
    }
    .detail-info {
        padding-bottom: 7rem;
    }
</style>