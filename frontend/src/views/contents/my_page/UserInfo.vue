<template>
  <div class="user-info">
    <ProfileImage :src="props.userInfo.image" />
    <div class="user-details">
      <h3>{{ props.userInfo.nickname }} <span class="badge">{{ userType }}</span></h3>
      <p>{{ props.userInfo.phone }}</p>
      <h4>이름</h4>
      <p>{{ props.userInfo.name }}</p>
      <h4>자기 소개</h4>
      <p>{{ props.userInfo.introduction }}</p>
    </div>
  </div>
</template>

<script setup>
import ProfileImage from './ProfileImage.vue';
import { ref, defineProps } from 'vue';

const props = defineProps({
  userInfo: Object
});
const userType = ref('');

const storedRoles = localStorage.getItem('Roles');
if (storedRoles) {
  const roles = storedRoles.split(',');
  if (roles.includes('ADMIN')) {
    userType.value = 'ADMIN';
  } else if (roles.includes('COMPANY')) {
    userType.value = 'COMPANY';
  } else if (roles.includes('CUSTOMER')) {
    userType.value = 'CUSTOMER';
  } else {
    userType.value = 'NOT AUTHORIZED';
  }
}

</script>

<style scoped>
.user-info {
  display: flex;
  padding: 30px;
  background-color: white;
  border-radius: 10px;
  margin-bottom: 20px;
}
.user-details {
  margin-left: 30px;
}
.badge {
  margin-left: 30px;
  background-color: #b3e5fc;
  border-radius: 15px;
  padding: 5px 15px;
}

p {
  margin-top: -10px;
}
</style>
