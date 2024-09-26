<template>
  <div v-if="isVisible" class="modal-overlay" @click.self="closeModal">
    <div class="modal-content">
      <button @click="closeModal">Close</button>
      <h2>{{ title }}</h2>
      <p>{{ message }}</p>
    </div>
  </div>
</template>

<script setup>
import { ref, watch, defineProps, defineEmits } from 'vue';

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
  background: rgba(0, 0, 0, 0.5);
  display: flex;
  justify-content: center;
  align-items: center;
}

.modal-content {
  background: white;
  padding: 20px;
  border-radius: 5px;
}
</style>