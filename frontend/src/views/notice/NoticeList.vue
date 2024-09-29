<template>
    <div class="notice-list-section">
      <h2>공지사항</h2>
      <div class="notice-list">
        <div class="notice-header">
          <span>개수 : {{ totalItems }}개</span>
          <button @click="goToCreatenotice" class="create-btn" v-if="isAdmin">등록</button>
  
        </div>
  
        <!-- 상단 고정 공지사항 테이블 -->
        <ul v-if="fixedItems.length > 0">
          <!-- <h3>상단 고정</h3> -->
          <li v-for="item in fixedItems" :key="item.id" class="notice-item" @click="goTonoticeRead(item)">
            <span class="notice-type">필독</span>
            <span class="notice-title">{{ item.title }}</span>
            <span class="notice-date">{{ formatDate(item.createdAt) }}</span>
          </li>
        </ul>
  
        <!-- 일반 공지사항 테이블 -->
        <ul>
          <!-- <h3>일반 공지사항</h3> -->
          <li v-for="item in paginatedItems" :key="item.id" class="notice-item" @click="goTonoticeRead(item)">
            <span class="notice-title">{{ item.title }}</span>
            <span class="notice-date">{{ formatDate(item.createdAt) }}</span>
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
  import NoticeDetail from './NoticeDetail.vue';
  
  // const sendItem = ref([])
  const router = useRouter()
  const noticeItems = ref([])
  const currentPage = ref(1)
  const itemsPerPage = 10
  const isAdmin = ref(false)
  
  const formatDate = (dateString) => {
    if (!dateString) return 'N/A'
    const options = { year: 'numeric', month: 'long', day: 'numeric', hour: '2-digit', minute: '2-digit' }
    return new Date(dateString).toLocaleDateString('ko-KR', options)
  }
  
  const checkRole = () => {
    const roleString = localStorage.getItem('Roles');   
    if(roleString){
      const roles = roleString.split(',');
      isAdmin.value = roles.includes('ADMIN');
    }else{
      isAdmin.value = false;
    }
  }
  
  const totalItems = computed(() => noticeItems.value.length)
  const totalPages = computed(() => Math.ceil((noticeItems.value.filter(item => !item.fixed).length) / itemsPerPage))
  
  const fixedItems = computed(() => noticeItems.value.filter(item => item.fixed))
  const nonFixedItems = computed(() => noticeItems.value.filter(item => !item.fixed))
  
  const paginatedItems = computed(() => {
    const start = (currentPage.value - 1) * itemsPerPage
    const end = start + itemsPerPage
    return nonFixedItems.value.slice(start, end)
  })
  
  const fetchNoticeItems = async () => {
    try {
      const response = await fetch('http://localhost:8888/notices');
      if(!response.ok) throw new Error('에러 발생');
      const data = await response.json();
      noticeItems.value = data;
    } catch (error) {
      console.error(error);
    }
  }
  
  const prevPage = () => {
    if (currentPage.value > 1) currentPage.value--
  }
  
  const nextPage = () => {
    if (currentPage.value < totalPages.value) currentPage.value++
  }
  
  const goToCreatenotice = () => {
    router.push('/api/v1/notice/post')
  }
  
  // const goTonoticeRead = (id) => {
  
  //   router.push(`/api/v1/notice/${id}`)
    
  // }
  
  const goTonoticeRead = (sendItem) => {
    console.log(sendItem);
    router.push({path: '/api/v1/notice/${sendItem.id}' ,
     query: { id: sendItem.id, 
              title: sendItem.title,
              content: sendItem.content,
              createdAt: sendItem.createdAt,
              memberId: sendItem.memberId,
              fixed: sendItem.fixed }})
    // router.push({name: 'NoticeDetail' , query: { id: item.id, title: item.title, content: item.content, createdAt: item.createdAt }})
  }
  
  
  onMounted(() => {
    fetchNoticeItems()
    checkRole()
  })
  </script>
  
    
    <style scoped>
    .notice-list-section {
      font-family: Arial, sans-serif;
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
    
    .notice-list {
      background-color: white;
      padding: 20px;
      border-radius: 8px;
      box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
    }
    
    .notice-header {
      display: flex;
      justify-content: space-between;
      align-items: center;
      margin-bottom: 15px;
    }
    
    .notice-header input {
      padding: 5px;
      border: 1px solid #ddd;
      border-radius: 4px;
    }
    
    ul {
      list-style-type: none;
      padding: 0;
    }
    
    .notice-item {
      display: flex;
      justify-content: space-between;
      padding: 10px 0;
      margin-bottom: 10px;
      background-color: #DDF5FF;  /* 목록 항목의 배경색 */
      border: 1px solid #B0DFF7;  /* 테두리 색상 */  
      border-radius: 8px; /* 원형 */ 
      cursor: pointer;
      transition: background-color 0.3s;
    }
    
    .notice-item:hover {
      background-color: #f5f5f5;
    }
    
    .notice-type {
    background-color: #f2bdbd; /* 유형의 배경색 */
    padding: 5px 12px;
    border-radius: 12px;
    font-size: 0.8em;
    color: #000000;  /* 텍스트 색상 */
    margin-left: 5px;
  }
  
  .notice-title {
    flex-grow: 1;
    margin: 0 15px;
    font-weight: bold;
    color: #333;  /* 제목 텍스트 색상 */
  }
  
  .notice-date {
    color: #888;
    font-size: 0.9em;
    margin-right: 10px;
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
      display: flex;
      justify-content: center;
    }
    </style>