<template>
    <div class="customer-review-container">
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
                    <div class="review-actions">
                        <button v-if="isCustomer" @click="handleReviewAction(review.id)" class="action-btn edit-delete-btn">리뷰 수정/삭제</button>
                    </div>
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
import { useRoute, useRouter } from 'vue-router';

const route = useRoute();
const router = useRouter();
const businessId = route.params.id;

const reviews = ref([]);
const totalReviews = ref(0);
const averageRating = ref(0);
const displayedCount = ref(10);
const selectedFilter = ref('default');
const showDropdown = ref(false);
const isCustomer = ref(false);

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
        // const response = await fetch(`http://localhost:8083/customerReviews?filter=${selectedFilter.value}`);
        const response = await fetch('http://localhost:8085/customerReviews');
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

const handleReviewAction = (reviewId) => {
    router.push({ 
        name: 'UpdateReview', 
        params: { reviewId: reviewId }
    });
};

const checkCustomerStatus = () => {
    const rolesString = localStorage.getItem('Roles');
    if (rolesString) {
        const roles = rolesString.split(',');
        isCustomer.value = roles.includes('CUSTOMER');
    } else {
        isCustomer.value = false;
    }
};

onMounted(() => {
    fetchReviews();
    checkCustomerStatus();
});
</script>

<style scoped>
.customer-review-container {
    max-width: 800px;
    margin: 0 auto;
    padding: 20px;
    padding-bottom: 100px;
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
    background-color: #e6f3ff;
    border-radius: 8px;
}

.review-header {
    display: flex;
    justify-content: space-between;
    align-items: center;
    margin-bottom: 10px;
}

.review-actions {
    display: flex;
    gap: 10px;
}

.action-btn {
    padding: 5px 10px;
    border: none;
    border-radius: 4px;
    cursor: pointer;
    font-size: 12px;
}

.edit-btn {
    background-color: #4CAF50;
    color: white;
}

.delete-btn {
    background-color: #f44336;
    color: white;
}

.load-more-btn {
    display: block;
    width: 100%;
    padding: 10px;
    margin-top: 30px;
    margin-bottom: 30px;
    background-color: #f0f0f0;
    border: none;
    border-radius: 5px;
    cursor: pointer;
    font-size: 16px;
    font-weight: bold;
    color: #333;
    transition: background-color 0.3s;
}

.load-more-btn:hover {
    background-color: #e0e0e0;
}

.edit-delete-btn {
    background-color: #4CAF50;
    color: white;
}
</style>