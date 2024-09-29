<template>
    <!-- 작성 logic이라 보면 될 것 같은데.. modal로 굳이? -->
    <div class="notice-section">
      <h2>공지 작성</h2>
      <div class="notice-form">
        <div class="dropdown-container">
            <label for="fixed">중요도</label>
            <select id="fixed" v-model="fixed" @change="change">
                <option value="필독">필독</option>
                <option value="일반">일반</option>
            </select>
        </div>
        <div class="form-group">
          <label for="question">공지제목</label>
          <input type="text" id="question" v-model="question" placeholder="제목을 입력하세요." />
        </div>
        <div class="form-group">
          <label for="answer">내용</label>
          <textarea id="answer" v-model="answer" placeholder="내용을 입력하세요."></textarea>
        </div>
        <div class="buttons">
          <button @click="savenotice" class="save-btn">등록</button>
          <button @click="cancel" class="cancel-btn">취소</button>
        </div>
      </div>
    </div>
</template>
  
<script setup>
  import { ref } from 'vue'
  import { useRouter } from 'vue-router'
  
  const router = useRouter()
  const fixed = ref('일반') // 기본값을 '일반'으로 설정

  const question = ref('')
  const answer = ref('')
      
  const change = (fixed) => {
    // backend에 쏴주기
  } 

  const goBack = () => {
    router.push('/api/v1/notice')
  }
  
  const savenotice = () => {
    // 여기에 저장 로직을 구현합니다.
    // console.log('저장된 Q&A:', { question: question.value, answer: answer.value })
    // API 호출 또는 상태 관리 로직을 추가할 수 있습니다.
    alert('공지가 등록되었습니다.');
    goBack()
  }
  
  const cancel = () => {
    // 취소 로직을 구현합니다.
    goBack()
  }
</script>
  
<style scoped>
  .notice-section {
    font-family: Arial, sans-serif;
    max-width: 600px;
    margin: 0 auto;
    padding: 20px;
    background-color: #f9f9f9;
    border-radius: 8px;
  }
  
  h2 {
    color: #333;
    margin-bottom: 20px;
  }
  
  .notice-form {
    background-color: white;
    padding: 20px;
    border-radius: 8px;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  }
  
  .form-group {
    margin-bottom: 15px;
  }
  
  label {
    display: block;
    margin-bottom: 5px;
    color: #666;
  }
  
  input[type="text"],
  textarea {
    width: 100%;
    padding: 8px;
    border: 1px solid #ddd;
    border-radius: 4px;
    font-size: 14px;
  }
  
  textarea {
    height: 100px;
    resize: vertical;
  }
  
  .buttons {
    display: flex;
    justify-content: flex-end;
    gap: 10px;
    margin-top: 20px;
  }
  
  button {
    padding: 8px 15px;
    border: none;
    border-radius: 4px;
    cursor: pointer;
    font-size: 14px;
  }
  
  .save-btn {
    background-color: #4CAF50;
    color: white;
  }
  
  .cancel-btn {
    background-color: #f44336;
    color: white;
  }
</style>