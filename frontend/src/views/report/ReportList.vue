<template>
  <div class="report-list-section">
    <h2>신고 목록</h2>
    <div class="report-list">
      <div class="report-header">
        <span>개수 : {{ filteredItems.length }}개</span>
        <input type="text" v-model="searchQuery" placeholder="ID로 검색하세요" />
      </div>
      <ul>
        <li v-for="item in paginatedItems" :key="item.id" class="report-item" @click="goToReportRead(item.id)">
          <span class="report-type">{{ item.type }}</span>
          <span class="report-title">{{ item.title }}</span>
          <span class="report-date">{{ item.date }}</span>
        </li>
      </ul>
      <div class="pagination">
        <button @click="prevPage" :disabled="currentPage === 1">&lt;</button>
        <span>{{ currentPage }} / {{ totalPages }}</span>
        <button @click="nextPage" :disabled="currentPage === totalPages">&gt;</button>
      </div>
      <button @click="goToCreatereport" class="create-btn">등록</button>
    </div>
  </div>
</template>

<script setup>
import { ref, computed, onMounted } from 'vue'
import { useRouter } from 'vue-router'

const router = useRouter()

const reportItems = ref([])
const currentPage = ref(1)
const itemsPerPage = 10
const searchQuery = ref('')

// 검색어에 따른 필터링된 리스트
const filteredItems = computed(() => {
  if (!searchQuery.value) {
    return reportItems.value // 검색어가 없을 경우 전체 리스트 반환
  }
  return reportItems.value.filter(item => (item.reporterId === parseInt(searchQuery.value)||(item.respondentId === parseInt(searchQuery.value))))
})

const totalItems = computed(() => filteredItems.value.length)
const totalPages = computed(() => Math.ceil(totalItems.value / itemsPerPage))

// 현재 페이지에 맞게 필터링된 리스트에서 페이징 처리된 결과 반환
const paginatedItems = computed(() => {
  const start = (currentPage.value - 1) * itemsPerPage
  const end = start + itemsPerPage
  return filteredItems.value.slice(start, end)
})

// 데이터를 불러오는 예시 함수
const fetchreportItems = async () => {
  reportItems.value = [
    { id: 1, type: '필독', title: '펫케어몰레터의 테마 선택 기준 여부', date: '24.09.01', reporterId: 3, respondentId: 1},
    { id: 2, type: '필독', title: '펫베스트와 행사 기획 범위', date: '24.08.13', reporterId: 5, respondentId: 4 },
    { id: 3, type: '일반', title: '도그웨딩브레이션 케이터링 서비스', date: '24.08.05', reporterId: 1, respondentId: 2 },
    // 더 많은 데이터 추가 가능
  ]
}

const prevPage = () => {
  if (currentPage.value > 1) currentPage.value--
}

const nextPage = () => {
  if (currentPage.value < totalPages.value) currentPage.value++
}

const goToCreatereport = () => {
  router.push('/report-post') // report 작성 페이지로 이동
}

const goToReportRead = (id) => {
  router.push(`/report-list/${id}`) // report 상세 읽기 페이지로 이동
}

onMounted(() => {
  fetchreportItems()
})
</script>

<style scoped>
.report-list-section {
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

.report-list {
  background-color: white;
  padding: 20px;
  border-radius: 8px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

.report-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 15px;
}

.report-header input {
  padding: 5px;
  border: 1px solid #ddd;
  border-radius: 4px;
}

ul {
  list-style-type: none;
  padding: 0;
}

.report-item {
  display: flex;
  justify-content: space-between;
  padding: 10px 0;
  border-bottom: 1px solid #eee;
  cursor: pointer;
  transition: background-color 0.3s;
}

.report-item:hover {
  background-color: #f5f5f5;
}

.report-type {
  background-color: #e0e0e0;
  padding: 2px 8px;
  border-radius: 12px;
  font-size: 0.8em;
}

.report-title {
  flex-grow: 1;
  margin: 0 15px;
}

.report-date {
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
