<template>
  <div class="review-form-container">
    <h1>리뷰 작성</h1>
    <form @submit.prevent="submitReview" class="review-form">
      <div class="form-group">
        <div class="title-rating-group">
          <div class="title-input">
            <label for="reviewTitle">리뷰 제목</label>
            <input v-model="reviewTitle" id="reviewTitle" type="text" required>
          </div>
          <div class="rating-input">
            <label>별점</label>
            <div class="rating-display">
              <span>{{ rating }}점</span>
            </div>
            <div class="star-rating">
              <span v-for="i in 5" :key="i" @click="setRating(i)" :class="{ 'filled': rating >= i }">★</span>
            </div>
          </div>
        </div>
      </div>
      <div class="form-group">
        <label for="reviewContent">리뷰 내용</label>
        <textarea v-model="reviewContent" id="reviewContent" rows="5" required></textarea>
      </div>
      <div class="button-group">
        <button type="submit" class="submit-btn">작성하기</button>
        <button type="button" @click="cancel" class="cancel-btn">취소</button>
      </div>
    </form>
  </div>
</template>

<script setup>
import { ref } from 'vue';
import { useRoute, useRouter } from 'vue-router';
// import axios from 'axios';

const route = useRoute();
const router = useRouter();
const eventId = ref(route.params.eventId);

const reviewTitle = ref('');
const reviewContent = ref('');
const rating = ref(0);

const setRating = (value) => {
  rating.value = value;
};

// const submitReview = async () => {
//   try {
//     await axios.post('/api/reviews', {
//       eventId: eventId.value,
//       title: reviewTitle.value,
//       content: reviewContent.value,
//       rating: rating.value
//     });
//     router.push({ name: 'CustomerReview' }); // 여기를 수정
//   } catch (error) {
//     console.error('리뷰 작성 실패:', error);
//     alert('리뷰 작성에 실패했습니다. 다시 시도해 주세요.');
//   }
// };

const cancel = () => {
  router.push({ name: 'CustomerReview' }); 
};
</script>

<style scoped>
.review-form-container {
  max-width: 800px;
  margin: 0 auto;
  padding: 20px;
  padding-bottom: 100px; /* footer를 고려한 여백 */
  background-color: #e6f3ff; /* 하늘색 배경 */
  border-radius: 8px;
}

.review-form {
  display: flex;
  flex-direction: column;
  gap: 20px;
}

.form-group {
  display: flex;
  flex-direction: column;
  gap: 5px;
}

.title-rating-group {
  display: flex;
  justify-content: space-between;
  align-items: flex-end;
}

.title-input {
  flex: 1;
  margin-right: 20px;
}

.rating-input {
  display: flex;
  flex-direction: column;
  align-items: flex-end;
}

.rating-display {
  font-size: 18px;
  font-weight: bold;
  margin-bottom: 5px;
}

label {
  font-weight: bold;
}

input, textarea {
  width: 100%;
  padding: 10px;
  border: 1px solid #ccc;
  border-radius: 4px;
}

.star-rating {
  font-size: 24px;
  cursor: pointer;
}

.star-rating span {
  color: #ddd;
}

.star-rating span.filled {
  color: gold;
}

.button-group {
  display: flex;
  justify-content: space-between;
  margin-top: 20px;
}

button {
  padding: 10px 20px;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  font-weight: bold;
}

.submit-btn {
  background-color: #4CAF50;
  color: white;
}

.cancel-btn {
  background-color: #f44336;
  color: white;
}
</style>
