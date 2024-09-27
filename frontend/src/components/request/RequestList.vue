<template>
  <main>
    <h1>내 요청서</h1>
    <div>
  <b-button v-b-modal.modal-1>Launch demo modal</b-button>

  <div>
  <!-- Using modifiers -->
  <b-button v-b-modal.my-modal>Show Modal</b-button>

  <!-- Using value -->
  <b-button v-b-modal="'my-modal'">Show Modal</b-button>

  <!-- The modal -->
  <b-modal id="my-modal">Hello From My Modal!</b-modal>
</div>
</div>

     <!-- 모달 예제 -->
     <div>
        <b-card title="모달 예제" class="mb-4">
           <b-button variant="primary" @click="showModal">모달 열기</b-button>
          <div v-if="isModalVisible"></div>
         </b-card>
        <b-modal style="width:800px; height:800px; background-color:red"
          id="example-modal"
          title="BootstrapVue 모달"
         
        >
          <p class="my-4">이것은 모달 창으로 필요한 정보를 여기에 표시하시요.</p>
          <template #modal-footer="{ ok, cancel }">
            <b-button variant="secondary" @click="cancel">취소</b-button>
            <b-button variant="primary" @click="ok">확인</b-button>
          </template>
        </b-modal>
        <b-table striped hover :items="items" :fields="fields" @row-clicked="goToDetailModal">
          <!-- <b-button variant="primary" @click="showModal">모달 열기</b-button>   -->
      </b-table>/
    </div>

    <div class="container mt-4">
        <b-modal>
          <p class="my-4">모달^^</p>
          <input type="text" v-model="modalText">
          <template #modal-footer="{ ok, cancel }">
            <b-button variant="secondary" @click="cancel">취소</b-button>
            <b-button variant="primary" @click="ok">확인</b-button>
          </template>
        </b-modal>
    </div>
    <div class="button-container">
      <b-button-group>
        <b-button
          class="custom-btn list-before"
          @click="beforepage"
          :disabled="currentPage === 1"
          ><<</b-button
        >
        <b-button class="custom-btn list">목록 보기</b-button>
        <b-button
          class="custom-btn list-after"
          @click="afterpage"
          :disabled="currentPage === totalPages"
          >>></b-button
        >
      </b-button-group>
    </div>
  </main>
</template>

<script setup>
import { ref } from "vue";
// import { useModal } from 'bootstrap-vue-3';

// const { showModal, hideModal } = useModal();

const isModalVisible = ref(false);

const showModal = () => {
  console.log("showModal");
  console.log(isModalVisible.value);
    isModalVisible.value = true;
};
// const showModal = () => {
//       isModalVisible.value = true;
//     };
const modalText = ref("");
const goToDetailModal = () => {
    isModalVisible.value = true;

};

const fields = [
  { key: "No", label: "No" },
  { key: "company", label: "요청한 업체" },
  { key: "event", label: "요청 이벤트" },
  { key: "date", label: "신청 날짜" },
  { key: "status", label: "진행 상황" },
  { key: "category", label: "해당 이벤트" },
];

const items = ref([
  {
    No: 1,
    company: "펫파티플래너",
    event:
      "서울에서 반려동물 생일 파티를 기획하려고 합니다. 귀사의 생일 패키지에 대해 더 알고 싶습니다",
    date: "2024-08-27T10:00:00",
    status: "확인 중",
    category: "반려동물 생일파티",
  },
  {
    No: 2,
    company: "해피펫모먼트",
    event:
      "부산에서 반려동물 돌잔치 기획을 검토 중입니다. 귀사의 돌잔치 패키지를 자세히 알고 싶습니다.",
    date: "2024-08-13T10:00:00",
    status: "확인 중",
    category: "돌잔치",
  },
  {
    No: 3,
    company: "펫케스타",
    event:
      "대구에서 풀파티를 개최하고 싶습니다. 귀사의 풀파티 패키지와 제공 서비스에 대해 문의드립니다.",
    date: "2024-06-13T10:00:00",
    status: "승인 완료",
    category: "품파티",
  },
]);
const currentPage = ref(1);
const totalPages = ref(3);

const beforepage = () => {
  if (currentPage.value > 1) currentPage.value--;
};

const afterpage = () => {
  if (currentPage.value < totalPages.value) currentPage.value++;
};

// const selectedRequest = ref(null);

// const openModal = (item) => {
//   selectedRequest.value = item;
//   showModal('request-modal');
// };

// const closeModal = () => {
//   hideModal('request-modal');
//   selectedRequest.value = null;
// };
// return { requests, fields, selectedRequest, openModal, closeModal };


</script>
<style scoped>
h1 {
  color: #49d5ff;
  font-family: Jua;
  margin: 60px;
}
main {
  padding: 20px;
  max-width: 80%;
  margin: auto;
}
.fields {
  text-align: inherit;
}
.button-container {
  display: flex;
  justify-content: center;
}
.button-container .b-button-group {
  align-items: center;
}
.button-container .custom-btn {
  display: block;
  margin: 20px auto;
  background-color: #fff;
  border: 2px solid #87ceeb;
  border-radius: 5px;
  color: #87ceeb;
  cursor: pointer;
}
.button-container .custom-btn.list-before,
.button-container .custom-btn.list-after {
  font-weight: bold;
}
.event-link {
  cursor: pointer;
}
.modal-content {
  padding: 15px;
}
.mb-2 {
  margin-bottom: 10px;
}
.modal-content {
    background-color: #87ceeb;
}
</style>
