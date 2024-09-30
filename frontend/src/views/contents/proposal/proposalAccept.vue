<template>
    <h1>받은 기획서</h1>
      <main>
        <div class="proposal-read">
          <p :style="{ textAlign: 'right' }">개수: {{ totalProposal }}개</p>
          <div v-if="items.length">
            <b-table
              striped
              hover
              :items="items"
              :fields="fields"
              @row-clicked="showDetails"
            >
            </b-table>
          </div>
          <div v-else>데이터를 불러오는 중...</div>
        </div>
        <b-modal
          v-model="showModal"
          title="기획서 상세 정보"
          size="lg"
          class="custom-modal"
          ok-title="승인"
          cancel-title="거절"
          ok-variant="primary"
          cancel-variant="danger"
          @ok="acceptProposal"
          @cancel="deleteProposal">
          <template v-if="selectedItem">
            <div class="detail-item">
              <strong>요청 이벤트</strong> {{ selectedItem.content }}
            </div>
            <div class="detail-item">
              <strong>희망 날짜</strong> {{ selectedItem.hope_time }}
            </div>
            <div class="detail-item">
              <strong>희망 비용</strong> {{ selectedItem.hope_cost }}
            </div>
            <div class="detail-item">
              <strong>희망 장소</strong> {{ selectedItem.hope_location }}
            </div>
            <div class="detail-item">
              <strong>진행 상황</strong> {{ selectedItem.status }}
            </div>
          </template>
          <template #modal-footer>
          </template>
        </b-modal>
      </main>
  </template>
  
  <script setup>
  import { ref, onMounted } from "vue";
  
  const items = ref([]);
  const totalProposal = ref(0);

  const fields = [
    { key: "content", label: "요청 이벤트" },
    { key: "hope_time", label: "희망 날짜" },
    { key: "hope_cost", label: "희망 비용" },
    { key: "hope_location", label: "희망 장소" },
    { key: "status", label: "진행 상황" }
  ];
  
  
  const showModal = ref(false);
  const selectedItem = ref(null);
  
  
  const showDetails = (item) => {
    selectedItem.value = item;
    showModal.value = true;
  };
  function acceptProposal() {
    return new Promise((resolve) => {
        alert("견적서 작성으로 넘어갑니다!");
        console.log('기획서 수락! 견적서 작성으로 넘어감');
        showModal.value = false;
        resolve();
    }).then(() => {
        router.push('/estimate');
    })};

  const deleteProposal = () => {
    if (selectedItem.value) {
      items.value = items.value.filter((item) => item !== selectedItem.value);
      showModal.value = false;
      totalProposal.value = items.value.length;
    }
  };
  
  const fetchProposal = async () => {
    try {
      const proposal = await fetch("http://localhost:8888/proposal");
      if (!proposal.ok) {
        throw new Error("네트워크 응답이 올바르지 않습니다.");
      }
      const data = await proposal.json();
      items.value = data;
      totalProposal.value = items.value.length;
    } catch (error) {
      console.error("기획서 데이터를 가져오는 중 오류 발생:", error);
    }
  };
  onMounted(() => {
    fetchProposal();
  });
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
  
  /* 모달창 css */
  .custom-modal ::v-deep .modal-content {
    background-color: #f0f8ff; /* 연한 하늘색 배경 */
    border-radius: 15px;
  }
  
  .custom-modal ::v-deep .modal-header {
    background-color: #87ceeb; /* 하늘색 헤더 */
    color: white;
    border-top-left-radius: 15px;
    border-top-right-radius: 15px;
  }
  
  .custom-modal ::v-deep .modal-title {
    text-align: center;
    font-weight: bold;
  }
  
  .custom-modal ::v-deep .modal-body {
    padding: 20px;
  }
  .detail-item {
    display: flex;
    justify-content: space-between;
    margin-bottom: 15px;
    border-bottom: 1px solid #e0e0e0;
    padding-bottom: 10px;
  }
  
  .detail-item:last-child {
    border-bottom: none;
  }
  
  .detail-item strong {
    font-weight: bold;
    color: #4a4a4a;
  }
  </style>
  