<template>
    <section class="container">
        <h2>hi</h2>
        <!-- <DetailTitle/> -->
        <!-- <DetailInfo 
            :gatherImg="gatherDetail?.gatherImg"
            :gatherName="gatherDetail?.gathername"
        /> -->
        
        <article class = "detail-info">
            <!-- <DetailDescription :gatherDetail="gatherDetail"/> -->
            <!-- <DetailBtn 
                :gatherId="gatherDetail?.id"
                :show="show"/> -->
        </article>
    </section>
</template>

<script setup>
    import { ref, onMounted, onBeforeMount } from 'vue';
    import { useRoute } from 'vue-router'; 

    const currentRoute = useRoute();    
    const gatherList = ref([]);
    const gatherDetail = ref(null);
    const show = ref('');

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
    const checkUserId = () => {
        const userId = localStorage.getItem('userid');        
        show.value = gatherDetail.value?.isLeader ?? false;
    }    

    onMounted(async () => {
        await fetchGatherDetail();
        checkUserId();
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