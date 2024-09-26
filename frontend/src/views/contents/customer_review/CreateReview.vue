<template>
  <div>
    <h1>리뷰 작성</h1>
    <p>이벤트 ID: {{ eventId }}</p>
    <!-- 여기에 리뷰 작성 폼 등을 추가할 수 있습니다 -->
  </div>
</template>

<script setup>
import { ref } from 'vue';
import { useRoute } from 'vue-router';

const route = useRoute();
const eventId = ref(route.params.eventId);

// eventId를 사용하여 리뷰 작성 로직 구현
</script>
