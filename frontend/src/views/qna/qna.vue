<template>
    <!-- <main>        
        <RouterView/>
    </main> -->
    <!-- <h1>문의하기</h1> -->
    <!-- 목록 페이지,  -->
    <div>
        <qnaForm/>
    </div>

    <div>
        <qnaList/>
    </div>

    <div>
        <QnaRegisterModal/>
    </div>

</template>

<script setup>
    import qnaList from './QnaList.vue';
    // import qnaForm from './components/qnaForm.vue';
    // import QnaRegisterModal from './components/QnaRegisterModal.vue';
    // import AnswerRegisterModal from './components/AnswerRegisterModal.vue';
    // import { RouterView } from 'vue-router';
</script>

<style scoped>   

</style>