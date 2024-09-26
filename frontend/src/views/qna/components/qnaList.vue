<template>
    <div class="qna-list-section">
      <h2>QnA 목록 조회 - 답변 완료 후(검색)</h2>
      <div class="qna-list">
        <div class="qna-header">
          <span>개수 : {{ totalItems }}개</span>
          <input type="text" v-model="searchQuery" placeholder="검색어를 입력하세요" @input="searchQnA" />
        </div>
        <ul>
          <li v-for="item in paginatedItems" :key="item.id" class="qna-item" @click="goToQnaRead(item.id)">
            <span class="qna-type">{{ item.type }}</span>
            <span class="qna-title">{{ item.title }}</span>
            <span class="qna-date">{{ item.date }}</span>
          </li>
        </ul>
        <div class="pagination">
          <button @click="prevPage" :disabled="currentPage === 1">&lt;</button>
          <span>{{ currentPage }} / {{ totalPages }}</span>
          <button @click="nextPage" :disabled="currentPage === totalPages">&gt;</button>
        </div>
        <button @click="goToCreateQnA" class="create-btn">등록</button>
      </div>
    </div>
  </template>
  
  <script setup>
  import { ref, computed, onMounted } from 'vue'
  import { useRouter } from 'vue-router'
  
  const router = useRouter()
  
  const qnaItems = ref([])
  const currentPage = ref(1)
  const itemsPerPage = 10
  const searchQuery = ref('')
  
  const totalItems = computed(() => qnaItems.value.length)
  const totalPages = computed(() => Math.ceil(totalItems.value / itemsPerPage))
  
  const paginatedItems = computed(() => {
    const start = (currentPage.value - 1) * itemsPerPage
    const end = start + itemsPerPage
    return qnaItems.value.slice(start, end)
  })
  
  const fetchQnAItems = async () => {
    // 실제 구현에서는 API 호출로 대체해야 합니다
    qnaItems.value = [
      { id: 1, type: '미답변', title: '펫케어몰레터의 테마 선택 기준 여부', date: '24.09.01' },
      { id: 2, type: '미답변', title: '펫베스트와 행사 기획 범위', date: '24.08.13' },
      { id: 3, type: '답변완료', title: '도그웨딩브레이션 케이터링 서비스', date: '24.08.05' },
      // ... 더 많은 아이템 추가
    ]
  }
  
  const prevPage = () => {
    if (currentPage.value > 1) currentPage.value--
  }
  
  const nextPage = () => {
    if (currentPage.value < totalPages.value) currentPage.value++
  }
  
  const searchQnA = () => {
    // 실제 구현에서는 서버 측 검색 또는 클라이언트 측 필터링을 수행해야 합니다
    console.log('Searching for:', searchQuery.value)
  }
  
  const goToCreateQnA = () => {
    router.push('/qna-post') // QnA 작성 페이지로 이동
  }
  
  const goToQnaRead = (id) => {
    router.push(`/QnaRead/${id}`) // QnA 상세 읽기 페이지로 이동
  }
  
  onMounted(() => {
    fetchQnAItems()
  })
  </script>
  
  <style scoped>
  .qna-list-section {
    font-family: Arial, sans-serif;
    max-width: 800px;
    margin: 0 auto;
    padding: 20px;
    background-color: #f9f9f9;
    border-radius: 8px;
  }
  
  h2 {
    color: #333;
    margin-bottom: 20px;
  }
  
  .qna-list {
    background-color: white;
    padding: 20px;
    border-radius: 8px;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  }
  
  .qna-header {
    display: flex;
    justify-content: space-between;
    align-items: center;
    margin-bottom: 15px;
  }
  
  .qna-header input {
    padding: 5px;
    border: 1px solid #ddd;
    border-radius: 4px;
  }
  
  ul {
    list-style-type: none;
    padding: 0;
  }
  
  .qna-item {
    display: flex;
    justify-content: space-between;
    padding: 10px 0;
    border-bottom: 1px solid #eee;
    cursor: pointer;
    transition: background-color 0.3s;
  }
  
  .qna-item:hover {
    background-color: #f5f5f5;
  }
  
  .qna-type {
    background-color: #e0e0e0;
    padding: 2px 8px;
    border-radius: 12px;
    font-size: 0.8em;
  }
  
  .qna-title {
    flex-grow: 1;
    margin: 0 15px;
  }
  
  .qna-date {
    color: #888;
    font-size: 0.9em;
  }
  
  .pagination {
    display: flex;
    justify-content: center;
    align-items: center;
    margin-top: 20px;
  }
  
  .pagination button {
    background-color: #4CAF50;
    color: white;
    border: none;
    padding: 5px 10px;
    margin: 0 5px;
    cursor: pointer;
    border-radius: 4px;
  }
  
  .pagination button:disabled {
    background-color: #ddd;
    cursor: not-allowed;
  }
  
  .create-btn {
    background-color: #4CAF50;
    color: white;
    border: none;
    padding: 10px 15px;
    margin-top: 20px;
    cursor: pointer;
    border-radius: 4px;
    float: right;
  }
  </style>