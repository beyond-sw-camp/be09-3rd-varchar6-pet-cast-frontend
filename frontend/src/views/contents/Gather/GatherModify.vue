<template>
    <section class="container">
        <ModifyTitle/>

        <ModifyInfo
            :gatherImg="gatherDetail?.gatherImg"
            :gatherName="gatherDetail?.gathername"
        />
        <ModifyDescription :gatherDetail="gatherDetail"/>
        
        <ModifyBtn :gatherId="gatherDetail?.id"/>
    </section>
</template>

<script setup>
    import { ref, onMounted } from 'vue';
    import { useRoute } from 'vue-router';

    import ModifyTitle from '@/components/body/gather/modify/ModifyTitle.vue';
    import ModifyInfo from '@/components/body/gather/modify/ModifyInfo.vue';
    import ModifyDescription from '@/components/body/gather/modify/ModifyDescription.vue';
    import ModifyBtn from '@/components/body/gather/modify/ModifyBtn.vue';
    

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
        height: 150vh;
    }
    .detail-info {
        padding-bottom: 7rem;
    }
</style>