<template>
    <div class="report-section">
      <h2>신고</h2>
      <div v-if="reportData" class="report-content">
        
        <div class="content-section">
          <h3>내용</h3>
          <p>{{ reportData.content }}</p>
        </div>
        
        <div class="metadata">
            <p>작성일: {{ formatDate(reportData.createdAt) }}</p>
            <p>신고자: {{ reportData.reporterId }}</p>
            <p>피신고자: {{ reportData.respondentId }}</p>
        </div>
      </div>
      <div v-else class="loading">데이터를 불러오는 중...</div>
      <button @click="goBack" class="back-btn">목록으로 돌아가기</button>
    </div>
  </template>
  
  <script setup>
  import { ref, onMounted } from 'vue'
  import { useRoute, useRouter } from 'vue-router'
  
  const route = useRoute()
  const router = useRouter()
  
  const reportData = ref(null)

  const formatDate = (dateString) => {
    if (!dateString) return 'N/A'
    const options = { year: 'numeric', month: 'long', day: 'numeric', hour: '2-digit', minute: '2-digit' }
    return new Date(dateString).toLocaleDateString('ko-KR', options)
  }
  
  const fetchreportData = async () => {
    const id = route.params.id
    // 실제 구현에서는 API 호출로 대체해야 합니다
    setTimeout(() => {
      reportData.value = {
        id: id,
        content: "너무 최신기술이다.",
        reporterId: "Vue 신고자",
        respondentId: "Vue 피신고자",
        createdAt: "2024-09-26T10:30:00"
      }
    }, 1000)
  }
  
  const goBack = () => {
    router.push('/api/v1/report')
  }
  
  onMounted(() => {
    fetchreportData()
  })
  
  </script>
  
  <style scoped>
  .report-section {
    font-family: Arial, sans-serif;
    max-width: 800px;
    margin: 0 auto;
    padding: 20px;
    background-color: #f9f9f9;
    border-radius: 8px;
  }
  
  h2, h3 {
    color: #333;
    margin-bottom: 15px;
  }
  
  .report-content {
    background-color: white;
    padding: 20px;
    border-radius: 8px;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  }
  
  .question-section, .answer-section, .answer-form {
    margin-bottom: 20px;
  }
  
  p {
    color: #666;
    line-height: 1.6;
  }
  
  textarea {
    width: 100%;
    height: 100px;
    padding: 10px;
    border: 1px solid #ddd;
    border-radius: 4px;
    resize: vertical;
  }
  
  .submit-btn {
    background-color: #4CAF50;
    color: white;
    padding: 10px 15px;
    border: none;
    border-radius: 4px;
    cursor: pointer;
    font-size: 14px;
    margin-top: 10px;
  }
  
  .metadata {
    font-size: 0.9em;
    color: #888;
    border-top: 1px solid #eee;
    padding-top: 10px;
    margin-top: 20px;
  }
  
  .loading {
    text-align: center;
    color: #666;
    font-style: italic;
  }
  .back-btn {
  background-color: #4CAF50;
  color: white;
  border: none;
  padding: 10px 15px;
  margin-top: 20px;
  cursor: pointer;
  border-radius: 4px;
}
  </style>