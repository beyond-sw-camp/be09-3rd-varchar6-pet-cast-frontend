<template>
  <div class="mypage-container">
    <h2 class="title">마이페이지</h2>
    <template v-if="userInfo != null">
      <UserInfo :userInfo="userInfo" />
    </template>
    <PetProfileCardList :petProfiles="petProfiles" />
    <SectionList title="참여한 이벤트 목록" :items="events" emptyText="참여하시는 이벤트 목록이 없습니다." />
    <SectionList title="작성한 리뷰 목록" :items="reviews" emptyText="작성하신 리뷰가 없습니다." />
    <SectionList title="작성한 Q&A 목록" :items="qna" emptyText="작성하신 Q&A가 없습니다." /> 


  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import axios from 'axios';
import UserInfo from '../my_page/UserInfo.vue';
import PetProfileCardList from '../my_page/PetProfileCardList.vue';
import SectionList from '../my_page/SectionList.vue';

let userInfo = ref(null);
const events = ref([]);
const reviews = ref([]);
const qna = ref([]);

onMounted(
  async () => {
    try {
      const response = await axios.get(`http://localhost:8888/user-me`);
      userInfo.value = response.data;
    

    } catch (e) {
      console.log('got error with fetching user information for profile');
    }
  }
)

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

</script>

<style scoped>
.mypage-container {
  max-width: 600px;
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