<template>
  <div v-if="isVisible" class="modal-overlay" @click.self="closeModal">
    <div class="modal-content">
      <h2 class="modal-title">{{ title }}</h2>
      <p class="modal-message">{{ message }}</p>
      <button class="modal-close-button" @click="closeModal">Close</button>
    </div>
  </div>
</template>

<script setup>
import { watch, defineProps, defineEmits } from 'vue';

const props = defineProps({
  isVisible: {
    type: Boolean,
    required: true,
  },
  title: {
    type: String,
    default: '알림',
  },
  message: {
    type: String,
    default: '메시지가 없습니다.',
  },
});

const emit = defineEmits();

const closeModal = () => {
  emit('update:isVisible', false);
};

// 모달의 가시성 상태를 감시
watch(() => props.isVisible, (newValue) => {
  if (newValue) {
    console.log('Modal opened');
  } else {
    console.log('Modal closed');
  }
});
</script>

<style scoped>
.modal-overlay {
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background: rgba(0, 0, 0, 0.7);
  display: flex;
  justify-content: center;
  align-items: center;
  z-index: 1000; /* 모달이 다른 요소 위에 보이도록 */
}

.modal-content {
  background: #fff;
  padding: 30px;
  border-radius: 10px;
  box-shadow: 0 4px 20px rgba(0, 0, 0, 0.1);
  max-width: 500px;
  width: 100%;
  animation: fadeIn 0.3s ease;
}

.modal-title {
  font-size: 1.5rem;
  margin-bottom: 15px;
  color: #333;
}

.modal-message {
  font-size: 1rem;
  margin-bottom: 20px;
  color: #666;
}

.modal-close-button {
  background: #007bff;
  color: white;
  border: none;
  border-radius: 5px;
  padding: 10px 20px;
  cursor: pointer;
  transition: background 0.3s;
}

.modal-close-button:hover {
  background: #0056b3; /* 호버 시 더 어두운 파란색 */
}

/* 모달 나타나는 애니메이션 */
@keyframes fadeIn {
  from {
    opacity: 0;
    transform: translateY(-20px);
  }
  to {
    opacity: 1;
    transform: translateY(0);
  }
}
</style>