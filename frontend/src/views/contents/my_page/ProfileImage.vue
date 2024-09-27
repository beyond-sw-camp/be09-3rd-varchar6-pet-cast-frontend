<template>
  <div class="profile-image-wrapper">
    <img :src="src" alt="프로필 이미지" class="profile-image" />
  </div>
</template>

<script setup>
import { defineProps } from 'vue';

const props = defineProps({
  src: {
    type: String,
    required: true,
  },
});
</script>

<style scoped>
.profile-image-wrapper {
  width: 80px;
  height: 80px;
  border-radius: 50%;
  overflow: hidden;
  display: flex;
  justify-content: center;
  align-items: center;
  background-color: #f0f0f0; /* 이미지 로딩 전의 배경색 */
}

.profile-image {
  width: 100%;
  height: 100%;
  object-fit: cover;
}
</style>
