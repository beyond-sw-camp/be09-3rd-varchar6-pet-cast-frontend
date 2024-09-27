<template>
  <div class="pet-profile-card">
    <div class="pet-info">
      <img src="../../../assets/image/profile/dogi.png" alt="애완동물 이미지" class="pet-image" />
      <div class="pet-details">
        <div class="name">{{ props.pet.name }}</div>
        <div class="properties">
          <div class="age">{{ props.pet.age }}살</div>
          <div class="gender">({{ gender }})</div>
        </div>
      </div>
    </div>
    <div class="pet-introduction">
      <p class="title">소개</p>
      <div class="content">
        {{ props.pet.introduction }}
      </div>
    </div>
  </div>
</template>

<script setup>  
import { ref, defineProps } from 'vue';

const gender = ref('not defined');
const props = defineProps({
  pet: Object
});

if (props.pet.gender) {
  if (props.pet.gender === 'FEMALE') {
    gender.value = 'W';
  } else {
    gender.value = 'M';
  }
}

</script>

<style scoped>
.pet-profile-card {
  border: 1px solid #91d5ff;
  width: 300px;
  padding: 10px;
  border-radius: 10px;
  background-color: #fff;
  display: flex;
  flex-direction: column;
}
.pet-info {
  width: 100%;
  display: flex;
  flex-direction: row;
  align-items: center;
}
.pet-image {
  width: 100px;
  height: 100px;
  border-radius: 50%;
  margin-right: 20px;
  margin-left: 10px;
  margin-top: 10px;
}
.title {
  font-weight: bold;
  font-size: 20px;
}
.pet-details {
  margin-top: 50px;
  margin-left: 10px;
}
.properties {
  display: flex;
  margin-top: 20px;
}
.name {
  font-weight: bolder;
}
.age {
  font-weight: bold;
}
.gender {
  font-weight: bold;
}
.pet-introduction {
  margin-top: 10px;
  margin-left: 30px;
}
</style>
