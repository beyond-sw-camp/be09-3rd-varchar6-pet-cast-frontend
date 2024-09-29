<template>
    <!-- 작성 logic이라 보면 될 것 같은데.. modal로 굳이? -->
    <div class="qa-section">
      <h2>문의하기</h2>
      <div class="qa-form">
        <div class="form-group">
          <label for="question">질문</label>
          <input type="text" id="question" v-model="question" placeholder="질문 내용을 입력하세요." />
        </div>
        <div class="form-group">
          <label for="answer">답변</label>
          <textarea id="answer" v-model="answer" placeholder="답변 내용을 입력하세요."></textarea>
        </div>
        <div class="buttons">
          <button @click="saveQA" class="save-btn">저장</button>
          <button @click="cancel" class="cancel-btn">취소</button>
        </div>
      </div>
    </div>
  </template>
  
  <script setup>
  import { ref } from 'vue'
  import { useRouter } from 'vue-router'
  
  const router = useRouter()
  
  const question = ref('')
  const answer = ref('')
      
  const goBack = () => {
    router.push('/api/v1/qna');
  }
  
  const saveQA = () => {
    // 여기에 저장 로직을 구현합니다.
    // console.log('저장된 Q&A:', { question: question.value, answer: answer.value })
    // API 호출 또는 상태 관리 로직을 추가할 수 있습니다.
    alert('문의가 등록되었습니다.');

    goBack()
  }
  
  const cancel = () => {
    // 취소 로직을 구현합니다.
    question.value = ''
    answer.value = ''

    goBack()
  }
  </script>
  
  <style scoped>
  .qa-section {
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
  
  .qa-form {
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