<template>
  <div class="mypage-container">
    <h2 class="title">마이페이지</h2>
    <template v-if="userInfo != null">
      <UserInfo :userInfo="userInfo" />
    </template>
    <PetProfileCardList :petProfiles="petProfiles" />
    <EventList :events="events" emptyText="참여하시는 이벤트 목록이 없습니다." />
    <ReviewList :reviews="reviews" emptyText="작성하신 리뷰가 없습니다." />
    <QnAList :qnas="qnas" emptyText="작성하신 Q&A가 없습니다." />
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import axios from 'axios';
import UserInfo from '../my_page/UserInfo.vue';
import PetProfileCardList from '../my_page/PetProfileCardList.vue';
import EventList from './custom_domain/EventList.vue';
import ReviewList from './custom_domain/ReviewList.vue';
import QnAList from './custom_domain/QnAList.vue';

let userInfo = ref(null);
let petProfiles = ref(null);
let events = ref(null);
let reviews = ref(null);
let qnas = ref(null);


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

    try {
      const customerEventListResponse = await axios.get(`http://localhost:8888/profile-events`);
      events.value = customerEventListResponse.data;

    } catch (e) {
      console.log('got error with fetching customerEvents list for profile');
    }

    try {
      const customerReviewListResponse = await axios.get(`http://localhost:8888/profile-reviews`);
      reviews.value = customerReviewListResponse.data;
    

    } catch (e) {
      console.log('got error with fetching customerReviews list for profile');
    }

    try {
      const petQnaListResponse = await axios.get(`http://localhost:8888/profile-qna`);
      console.log("petQnaListResponse.data: ", petQnaListResponse.data);
      qnas.value = petQnaListResponse.data;
    

    } catch (e) {
      console.log('got error with fetching customerQnA list for profile');
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