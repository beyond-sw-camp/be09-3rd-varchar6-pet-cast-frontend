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
let petProfiles = ref(null);
const events = ref([]);
const reviews = ref([]);
const qna = ref([]);

onMounted(
  async () => {
    try {
      const userProfileResponse = await axios.get(`http://localhost:8888/user-me`);
      userInfo.value = userProfileResponse.data;

    } catch (e) {
      console.log('got error with fetching user information for profile');
    }

    try {
      const petProfileListResponse = await axios.get(`http://localhost:8888/pet-profile`);
      petProfiles.value = petProfileListResponse.data;
    

    } catch (e) {
      console.log('got error with fetching pet information list for profile');
    }
  }
)


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