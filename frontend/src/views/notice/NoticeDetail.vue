<template>
    <div class="notice-section">
      <h2>공지</h2>
      <div v-if="noticeData" class="notice-content">
        <div class="dropdown-container">
            <label for="fixed">중요도</label>
            <select id="fixed" v-model="fixed" v-if="isAdmin">
                <option value="필독">필독</option>
                <option value="일반">일반</option>
            </select>
            <select id="fixed" v-model="fixed" disabled v-else>
                <option value="필독">필독</option>
                <option value="일반">일반</option>
            </select>
        </div>
        <div class="title-section">
          <h3>제목</h3>
          <p>{{ noticeData.title }}</p>
        </div>
        
        <div class="content-section">
          <h3>내용</h3>
          <p>{{ noticeData.content }}</p>
        </div>
        
        <div class="metadata">
          <p>작성일: {{ formatDate(noticeData.createdAt) }}</p>
          <p>작성자: {{ noticeData.memberId }}</p>
        </div>
      </div>
      <div v-else class="loading">데이터를 불러오는 중...</div>
      <button @click="noticeDelete" class="delete-btn" v-if="isAdmin">삭제</button>

      <button @click="goBack" class="back-btn">목록으로 돌아가기</button>
      <!-- <button @click="noticeModify" class="modify-btn" v-if="isAdmin">수정</button> -->
    </div>
  </template>
  
  <script setup>
  import { ref, onMounted } from 'vue'
  import { useRoute, useRouter } from 'vue-router'
  
  const route = useRoute()
  const router = useRouter()
  
  const noticeData = ref(null)
  const fixed = ref('일반') // 기본값을 '일반'으로 설정
  const isAdmin = ref(false)

  const checkRole = () => {
    const roleString = localStorage.getItem('Roles');   
    console.log(roleString);
    if(roleString){
      const roles = roleString.split(',');
      isAdmin.value = roles.includes('ADMIN');
    }else{
      isAdmin.value = false;
    }
  }

  const formatDate = (dateString) => {
    if (!dateString) return 'N/A'
    const options = { year: 'numeric', month: 'long', day: 'numeric', hour: '2-digit', minute: '2-digit' }
    return new Date(dateString).toLocaleDateString('ko-KR', options)
  }
  
  const fetchnoticeData = async () => {
    const id = route.params.id
    // 실제 구현에서는 API 호출로 대체해야 합니다
    setTimeout(() => {
      noticeData.value = {
        id: id,
        title: "Vue 3 Composition API의 장점은 무엇인가요?",
        content: "너무 최신기술이다.",
        createdAt: "2024-09-26T10:30:00",
        memberId: "Vue 초보자",
        fixed: fixed.value
      }
    }, 1000)
  }
  
  const goBack = () => {
    router.push('/notice-list')
  }
  
  const noticeDelete = () => {
    // 실제 구현에서는 API 호출로 대체해야 합니다
    setTimeout(() => {
      alert('공지가 삭제되었습니다.')
      router.push('/notice-list')
    }, 1000)
  }

  // const noticeModify = () => {
  //   // 실제 구현에서는 API 호출로 대체해야 합니다
  //   setTimeout(() => {
  //     alert('공지가 수정되었습니다.')
  //     router.push('/notice-list')
  //   }, 1000)
  // }

  onMounted(() => {
    fetchnoticeData()
    checkRole()
  })
  
  </script>
  
  <style scoped>
  .notice-section {
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
  
  .notice-content {
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
  display: flex;
  justify-content: center;

  }
  .delete-btn {
    background-color: #4CAF50;
    color: white;
    border: none;
    padding: 10px 15px;
    margin-top: 20px;
    cursor: pointer;
    border-radius: 4px;
    display:flex;
    justify-content: center;
  }
  </style>