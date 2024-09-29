<template>
    <div class="qa-section">
      <h2>문의하기</h2>
      <div v-if="qaData" class="qa-content">
        <div class="question-section">
          <h3>질문</h3>
          <p>{{ qaData.title }}</p>
          <p>{{ qaData.content }}</p>
        </div>
        
        <div v-if="qaData.answer" class="answer-section" >
          <h3>답변</h3>
          <p>{{ qaData.answer }}</p>
        </div>
        
        <div v-else-if="!qaData.answer && isCompany" class="answer-form">
          <h3>답변</h3>
          <textarea v-model="newAnswer" placeholder="답변을 입력하세요."></textarea>
          <button @click="submitAnswer" class="submit-btn">답변 등록</button>
        </div>
        
        <div class="metadata">
          <p>문의일: {{ formatDate(qaData.createdAt) }}</p>
          <p>질문자: {{ qaData.questionerId }}</p>
          <p v-if="qaData.answeredAt">답변일: {{ formatDate(qaData.answeredAt) }}</p>
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
  
  const qaData = ref('')
  const newAnswer = ref('')
  const isCompany = ref(false)

  const checkRole = () => {
    const roleString = localStorage.getItem('Roles');   
    // console.log(roleString);
    if(roleString){
      const roles = roleString.split(',');
      isCompany.value = roles.includes('COMPANY');
    }else{
      isCompany.value = false;
    }
  }  
  const formatDate = (dateString) => {
    if (!dateString) return 'N/A'
    const options = { year: 'numeric', month: 'long', day: 'numeric', hour: '2-digit', minute: '2-digit' }
    return new Date(dateString).toLocaleDateString('ko-KR', options)
  }
  
  // const fetchQAData = async () => {
  //   const id = route.params.id
  //   // 실제 구현에서는 API 호출로 대체해야 합니다
  //   setTimeout(() => {
  //     qaData.value = {
  //       id: id,
  //       question: "Vue 3 Composition API의 장점은 무엇인가요?",
  //       answer: null,
  //       createdAt: "2024-09-26T10:30:00",
  //       questioner: "Vue 초보자",
  //       answeredAt: null,
  //       questionerId: 1
  //     }
  //   }, 1000)
  // }
  
  const submitAnswer = async () => {
    if (newAnswer.value.trim()) {
      qaData.value.answer = newAnswer.value
      qaData.value.answeredAt = new Date().toISOString()
      newAnswer.value = ''
    } else {
      alert('답변을 입력해주세요.')
    }
  }
  
  const goBack = () => {
    router.push('/api/v1/qna')
  }
  
  onMounted(() => {
    // fetchQAData()
    checkRole()
    qaData.value = {
      id: route.query.id,
      title: route.query.title,
      content: route.query.content,
      createdAt: route.query.createdAt,
      answeredAt: route.query.answeredAt,
      answer: route.query.answer,
      companyId: route.query.companyId,
      questionerId: route.query.questionerId,
      answeredId: route.query.answererId
    }
  })
  
  </script>
  
  <style scoped>
  .qa-section {
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
  
  .qa-content {
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