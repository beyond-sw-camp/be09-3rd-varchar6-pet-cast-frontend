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
                    <b-table striped hover :items="items" :fields="fields"></b-table>
            </div>
            <div class="paging">
                <b-button-toolbar key-nav aria-label="Toolbar with button groups">
                    <b-button-group class="mx-1">
                        <b-button>&lsaquo;</b-button>
                        </b-button-group>
                    <b-button-group class="mx-1">
                        <b-button>현재 페이지 번호</b-button>                
                    </b-button-group>
                <b-button-group class="mx-1">
                    <b-button>&rsaquo;</b-button>
                    </b-button-group>
                </b-button-toolbar>
            </div>
        </div>
    </section>

</template>

<script setup>
    import { ref } from 'vue';


    const items = ref([
        { 'index': 1, '모임리더': '강형욱', '모임명': '강아지를 사랑하는 모임', '생성일자': '2024-08-17T10:00:00', '해당 이벤트': '반려동물 생일파티' },
        { 'index': 2, '모임리더': '김철수', '모임명': '반려묘와의 생활', '생성일자': '2024-09-05T14:00:00', '해당 이벤트': '고양이 털 관리 세미나' },
        { 'index': 3, '모임리더': '박영희', '모임명': '새들을 사랑하는 모임', '생성일자': '2024-07-22T09:30:00', '해당 이벤트': '앵무새 훈련 워크숍' },
        { 'index': 4, '모임리더': '이민수', '모임명': '물고기 키우기 마스터', '생성일자': '2024-06-11T18:00:00', '해당 이벤트': '수족관 관리 및 청소 팁' },
        { 'index': 5, '모임리더': '정하나', '모임명': '특수동물 동호회', '생성일자': '2024-05-28T15:00:00', '해당 이벤트': '파충류 사육 노하우 공유' },
        { 'index': 6, '모임리더': '이민수', '모임명': '물고기 키우기 마스터', '생성일자': '2024-06-11T18:00:00', '해당 이벤트': '수족관 관리 및 청소 팁' },
        { 'index': 7, '모임리더': '정하나', '모임명': '특수동물 동호회', '생성일자': '2024-05-28T15:00:00', '해당 이벤트': '파충류 사육 노하우 공유' },
        { 'index': 8, '모임리더': '정하나', '모임명': '특수동물 동호회', '생성일자': '2024-05-28T15:00:00', '해당 이벤트': '파충류 사육 노하우 공유' },
        { 'index': 9, '모임리더': '정하나', '모임명': '특수동물 동호회', '생성일자': '2024-05-28T15:00:00', '해당 이벤트': '파충류 사육 노하우 공유' },
        { 'index': 10, '모임리더': '정하나', '모임명': '특수동물 동호회', '생성일자': '2024-05-28T15:00:00', '해당 이벤트': '파충류 사육 노하우 공유' }
    ]);

    const fields = ref([
        { key: 'index', label: '' },
        { key: '모임리더', label: '모임 리더' },
        { key: '모임명', label: '모임 명' },
        { key: '생성일자', label: '생성 일자' },
        { key: '해당 이벤트', label: '해당 이벤트' }
    ]);
</script>

<style scoped>
    .section{
        overflow: auto;
        height: 100vh;
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
    }
</style>