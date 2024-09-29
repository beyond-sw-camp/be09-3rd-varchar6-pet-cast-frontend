<template>
    <div class="qna-list-section">
      <h2>문의하기</h2>
      <button @click="goToCreateQnA" class="create-btn">등록</button>
      <div class="qna-list">
        <div class="qna-header">
          <span>개수 : {{ filteredItems.length }}개</span>
          <!-- <input type="text" v-model="searchQuery" placeholder="검색어를 입력하세요" @input="searchQnA" /> -->
        </div>
        <ul>
          <li v-for="item in paginatedItems" :key="item.id" class="qna-item" @click="goToQnaRead(item)">
            <!-- <span class="qna-type" v-if="item.type == '미답변'" style="background-color: #ffffff">{{ item.type }}</span> -->
            <span class="qna-type" v-if="!item.answered" style="background-color: #ffffff">미답변</span>
            <!-- <span class="qna-type" v-else>{{ item.type }}</span> -->
            <span class="qna-type" v-else>답변완료</span>
            <span class="qna-title">{{ item.title }}</span>
            <span class="qna-date">{{ formatDate(item.createdAt) }}</span>
          </li>
        </ul>
        <div class="pagination">
          <button @click="prevPage" :disabled="currentPage === 1">&lt;</button>
          <span>{{ currentPage }} / {{ totalPages }}</span>
          <button @click="nextPage" :disabled="currentPage === totalPages">&gt;</button>
        </div>
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
  //(업체 아이디는 업체 상세 페이지에서 받아옴(받아온다면)
  // const companyId = ref(3)
  // 검색어에 따른 필터링된 리스트
  const filteredItems = computed(() => {
  if (!searchQuery.value) {
    return qnaItems.value;
    // 기본적으로 업체
    // return qnaItems.value.filter(companyId);
  }
  return qnaItems.value.filter(item => (item.questionerId === parseInt(searchQuery.value)))
  })
  const totalItems = computed(() => qnaItems.value.length)
  const totalPages = computed(() => Math.ceil(totalItems.value / itemsPerPage))
  
  const paginatedItems = computed(() => {
    const start = (currentPage.value - 1) * itemsPerPage
    const end = start + itemsPerPage
    return filteredItems.value.slice(start, end)
  })
  
  const formatDate = (dateString) => {
    if (!dateString) return 'N/A'
    const options = { year: 'numeric', month: 'long', day: 'numeric', hour: '2-digit', minute: '2-digit' }
    return new Date(dateString).toLocaleDateString('ko-KR', options)
  }

  const fetchQnAItems = async () => {
    // 실제 구현에서는 API 호출로 대체해야 합니다
    // qnaItems.value = [
    //   { id: 1, type: '미답변', title: '펫케어몰레터의 테마 선택 기준 여부', date: '24.09.01' },
    //   { id: 2, type: '미답변', title: '펫베스트와 행사 기획 범위', date: '24.08.13' },
    //   { id: 3, type: '답변완료', title: '도그웨딩브레이션 케이터링 서비스', date: '24.08.05' },
    //   { id: 1, type: '미답변', title: '펫케어몰레터의 테마 선택 기준 여부', date: '24.09.01' },
    //   { id: 2, type: '미답변', title: '펫베스트와 행사 기획 범위', date: '24.08.13' },
    //   { id: 3, type: '답변완료', title: '도그웨딩브레이션 케이터링 서비스', date: '24.08.05' },
    //   { id: 1, type: '미답변', title: '펫케어몰레터의 테마 선택 기준 여부', date: '24.09.01' },
    //   { id: 2, type: '미답변', title: '펫베스트와 행사 기획 범위', date: '24.08.13' },
    //   { id: 3, type: '답변완료', title: '도그웨딩브레이션 케이터링 서비스', date: '24.08.05' },
    //   // ... 더 많은 아이템 추가
    // ]
    try {
      const response = await fetch('http://localhost:8888/qnas')
      if(!response.ok) throw new Error('에러 발생');
      const data = await response.json();
      qnaItems.value = data;
    } catch(error){
      console.error(error);
    }
  }
  
  const prevPage = () => {
    if (currentPage.value > 1) currentPage.value--
  }
  
  const nextPage = () => {
    if (currentPage.value < totalPages.value) currentPage.value++
  }
  
  // const searchQnA = () => {
  //   // 실제 구현에서는 서버 측 검색 또는 클라이언트 측 필터링을 수행해야 합니다
  //   console.log('Searching for:', searchQuery.value)
  // }
  
  const goToCreateQnA = () => {
    router.push('/api/v1/qna/post') // QnA 작성 페이지로 이동
  }
  
  const goToQnaRead = (sendItem) => {
    router.push({path: '/api/v1/qna/${sendTiem.id}',
    query: {
        id : sendItem.id,
        title: sendItem.title,
        content: sendItem.content,
        createdAt: sendItem.createdAt,
        answeredAt: sendItem.answeredAt,
        answer: sendItem.answer,
        answered: sendItem.answered,
        companyId: sendItem.companyId,
        questionerId: sendItem.questionerId,
        answererId: sendItem.answererId
      }
    }
    ) // QnA 상세 읽기 페이지로 이동
  }
  
  onMounted(() => {
    fetchQnAItems()
  })
  </script>
  
  <style scoped>
.qna-list-section {
  font-family: 'Arial', sans-serif;
  max-width: 1200px;
  margin: 0 auto;
  padding: 20px;
  background-color: #f9f9f9;
  border-radius: 8px;
}

h2 {
  color: #333;
  margin-bottom: 20px;
  font-size: 24px;
  font-weight: bold;
  /* font-family: Jua; */
  display: flex;
  justify-content:flex-start
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
  width: 250px;
}

ul {
  list-style-type: none;
  padding: 0;
}

.qna-item {
  display: flex;
  justify-content: space-between;
  padding: 15px;
  margin-bottom: 10px;
  background-color: #DDF5FF;  /* 목록 항목의 배경색 */
  border: 1px solid #B0DFF7;  /* 테두리 색상 */
  border-radius: 8px;
  cursor: pointer;
  transition: background-color 0.3s;
}

.qna-item:hover {
  background-color: #C7E9FF; /* 호버 시 더 진한 파란색 */
}

.qna-type {
  background-color: #f6e7e7; /* 유형의 배경색 */
  padding: 5px 12px;
  border-radius: 12px;
  font-size: 0.8em;
  color: #000000;  /* 텍스트 색상 */
}

.qna-title {
  flex-grow: 1;
  margin: 0 15px;
  font-weight: bold;
  color: #333;  /* 제목 텍스트 색상 */
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
  padding: 10px 20px;
  margin: 0 5px;
  cursor: pointer;
  border-radius: 4px;
  font-size: 14px;
}

.pagination button:disabled {
  background-color: #ddd;
  cursor: not-allowed;
}

.create-btn {
  background-color: #4CAF50;
  color: white;
  border: none;
  padding: 10px 20px;
  margin-top: 10px;
  cursor: pointer;
  border-radius: 4px;
  float: right;
  display: flex;
  justify-content: center;
}

  </style>