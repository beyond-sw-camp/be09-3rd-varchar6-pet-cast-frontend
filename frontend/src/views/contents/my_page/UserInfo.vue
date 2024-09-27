<template>
  <div class="user-info">
    <ProfileImage :src="user.profileImage" />
    <div class="user-details">
      <h3>{{ user.username }} <span class="badge">{{ user.userType }}</span></h3>
      <p>{{ user.phoneNumber }}</p>
      <h4>이름</h4>
      <p>{{ user.name }}</p>
      <h4>자기 소개</h4>
      <p>{{ user.intro }}</p>
    </div>
  </div>
</template>

<script setup>
import ProfileImage from './ProfileImage.vue';
import { defineProps } from 'vue';

const props = defineProps({
  user: {
    type: Object,
    required: true,
  },
});
</script>

<style scoped>
.user-info {
  display: flex;
  padding: 20px;
  background-color: white;
  border-radius: 10px;
  margin-bottom: 20px;
}
.user-details {
  margin-left: 20px;
}
.badge {
  background-color: #b3e5fc;
  border-radius: 5px;
  padding: 2px 5px;
}
</style>
