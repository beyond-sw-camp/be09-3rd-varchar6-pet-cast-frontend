<template>
  <div class="mypage-container">
    <h2 class="title">마이페이지</h2>
    <UserInfo :user="user" />
    <PetProfileCardList :petProfiles="petProfiles" />
    <SectionList title="참여한 이벤트 목록" :items="events" emptyText="참여하시는 이벤트 목록이 없습니다." />
    <SectionList title="작성한 리뷰 목록" :items="reviews" emptyText="작성하신 리뷰가 없습니다." />
    <SectionList title="작성한 Q&A 목록" :items="qna" emptyText="작성하신 Q&A가 없습니다." />
  </div>
</template>

<script setup>
import { ref } from 'vue';
import UserInfo from '../my_page/UserInfo.vue';
import PetProfileCardList from '../my_page/PetProfileCardList.vue';
import SectionList from '../my_page/SectionList.vue';

const user = ref({
  profileImage: 'path-to-image', // 프로필 이미지 경로
  username: '도기아빠님',
  userType: 'customer',
  phoneNumber: '010-1234-5678',
  name: '김도기',
  intro: '안녕하세요! 도기, 루리 두아이 아빠입니다 ^^',
});

const petProfiles = ref([
  {
    id: 1,
    name: '도기',
    type: '강아지',
    age: 3,
    image: 'dog-image-path', // 강아지 프로필 이미지 경로
  },
  {
    id: 2,
    name: '루리',
    type: '고양이',
    age: 2,
    image: 'cat-image-path', // 고양이 프로필 이미지 경로
  },
]);

const events = ref([]);
const reviews = ref([]);
const qna = ref([]);
</script>

<style scoped>
.mypage-container {
  max-width: 800px;
  margin: 0 auto;
  margin-block: 30px;
  padding: 20px;
  background-color: #e6f7ff;
  border-radius: 10px;
}
.title {
  text-align: center;
  margin-bottom: 20px;
  font-size: 24px;
}
</style>