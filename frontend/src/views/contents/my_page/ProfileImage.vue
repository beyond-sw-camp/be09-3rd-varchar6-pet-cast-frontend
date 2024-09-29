<template>
  <div class="profile-image-wrapper">
    <!-- <img :src="src" alt="프로필 이미지" class="profile-image" /> -->
    <img src="../../../assets/image/profile/profile_sample_500x500.png" alt="프로필 이미지" class="profile-image" />
  </div>
</template>

<script setup>
import { defineProps } from 'vue';
import defaultProfileimg from '../../../assets/image/profile/default_user_profile_img.png'

const props = defineProps({
  src: {
    type: String,
    required: true,
    default: defaultProfileimg,
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
