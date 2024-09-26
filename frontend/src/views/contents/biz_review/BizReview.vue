<template>
    <div class="biz-review-container">
        <h2 class="review-title">리뷰</h2>
        <div class="review-summary">
            <div class="average-rating">
                <span class="star">★</span> {{ averageRating.toFixed(1) }} ({{ totalReviews }})
            </div>
            <div class="filter-dropdown">
                <button @click="toggleDropdown">{{ filterOptions[selectedFilter].label }} ▼</button>
                <ul v-if="showDropdown" class="dropdown-menu">
                    <li v-for="(option, key) in filterOptions" :key="key" @click="changeFilter(key)">
                        {{ option.label }}
                    </li>
                </ul>
            </div>
        </div>

        <div class="review-list">
            <div v-for="review in displayedReviews" :key="review.id" class="review-item">
                <div class="review-header">
                    <span class="review-rating">★ {{ review.rating.toFixed(1) }}</span>
                    <span class="review-author">{{ review.author }}</span>
                    <span class="review-date">{{ formatDate(review.date) }}</span>
                </div>
                <p class="review-content">{{ review.content }}</p>
            </div>
        </div>

        <button v-if="hasMoreReviews" @click="loadMoreReviews" class="load-more-btn">
            리뷰 더보기
        </button>
    </div>
</template>

<script setup>
import { ref, computed, onMounted } from 'vue';
import { useRoute } from 'vue-router';

const route = useRoute();
const businessId = route.params.id;

const reviews = ref([]);
const totalReviews = ref(0);
const averageRating = ref(0);
const displayedCount = ref(10);
const selectedFilter = ref('default');
const showDropdown = ref(false);

const filterOptions = {
    default: { label: '최신순', value: 'default' },
    highRating: { label: '별점 높은순', value: 'high' },
    lowRating: { label: '별점 낮은순', value: 'low' }
};

const displayedReviews = computed(() => reviews.value.slice(0, displayedCount.value));
const hasMoreReviews = computed(() => displayedCount.value < totalReviews.value);

const toggleDropdown = () => {
    showDropdown.value = !showDropdown.value;
};

const changeFilter = (filter) => {
    selectedFilter.value = filter;
    showDropdown.value = false;
    fetchReviews();
};

const loadMoreReviews = () => {
    displayedCount.value += 10;
};

const fetchReviews = async () => {
    try {
        // const response = await fetch(`http://localhost:8083/businessReviews?filter=${selectedFilter.value}`);
        const response = await fetch('http://localhost:8083/businessReviews');
        const data = await response.json();
        reviews.value = data.reviews;
        totalReviews.value = data.totalReviews;
        averageRating.value = data.averageRating;
        displayedCount.value = 10;
    } catch (error) {
        console.error('리뷰를 불러오는 중 오류가 발생했습니다:', error);
    }
};

const formatDate = (dateString) => {
    const date = new Date(dateString);
    return date.toLocaleDateString('ko-KR');
};

onMounted(() => {
    fetchReviews();
});
</script>

<style scoped>
.biz-review-container {
    max-width: 800px;
    margin: 0 auto;
    padding: 20px;
    padding-bottom: 100px; /* Footer를 고려한 여백 증가 */
}

.review-title {
    font-size: 24px;
    font-weight: bold;
    margin-bottom: 20px;
}

.review-summary {
    display: flex;
    justify-content: space-between;
    align-items: center;
    margin-bottom: 20px;
}

.average-rating {
    font-size: 24px;
    font-weight: bold;
}

.filter-dropdown {
    position: relative;
}

.dropdown-menu {
    position: absolute;
    top: 100%;
    right: 0;
    background-color: white;
    border: 1px solid #ccc;
    list-style-type: none;
    padding: 0;
    margin: 0;
}

.dropdown-menu li {
    padding: 10px;
    cursor: pointer;
}

.review-item {
    border-bottom: 1px solid #eee;
    padding: 15px;
    margin-bottom: 15px;
    background-color: #e6f3ff; /* 하늘색 배경 */
    border-radius: 8px;
}

.review-header {
    display: flex;
    justify-content: space-between;
    margin-bottom: 10px;
}

.load-more-btn {
    display: block;
    width: 100%;
    padding: 10px;
    margin-top: 30px; /* 상단 여백 증가 */
    margin-bottom: 30px; /* 하단 여백 추가 */
    background-color: #f0f0f0;
    border: none;
    border-radius: 5px; /* 버튼 모서리 둥글게 */
    cursor: pointer;
    font-size: 16px; /* 글자 크기 증가 */
    font-weight: bold; /* 글자 굵게 */
    color: #333; /* 글자 색상 */
    transition: background-color 0.3s; /* 호버 효과를 위한 전환 */
}

.load-more-btn:hover {
    background-color: #e0e0e0; /* 호버 시 배경색 변경 */
}
</style>