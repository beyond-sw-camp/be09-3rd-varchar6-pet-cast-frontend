<template>
    <div class="biz-mypage">
        <div class="left-section">
            <div class="select-business">
                업체를 선택해주세요
                <p>관리할 업체를 선택해주세요</p>
            </div>
        </div>
        <div class="right-section">
            <div class="business-cards">
                <div v-for="business in businesses" :key="business.id" class="business-card" @click="goToBizDetail(business.id)">
                    <img :src="business.image" :alt="business.name">
                    <p>{{ business.name }}</p>
                </div>
            </div>
        </div>
    </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import { useRouter } from 'vue-router';

const businesses = ref([]);
const router = useRouter();

const fetchBusinesses = async () => {
    try {
        const response = await fetch(`http://localhost:8888/bizs`);
        if (!response.ok) {
            throw new Error('네트워크 응답이 올바르지 않습니다.');
        }
        const data = await response.json();
        businesses.value = data;
    } catch (error) {
        console.error('업체 데이터를 가져오는 중 오류가 발생했습니다:', error);
    }
};

const goToBizDetail = (id) => {
    router.push({ name: 'BizDetail', params: { id: id } });
};

onMounted(fetchBusinesses);
</script>

<style scoped>
.biz-mypage {
    display: flex;
    height: 100vh;
}

.left-section {
    width: 30%;
    padding: 20px;
    background-color: #f0f8ff;
    display: flex;
    align-items: center;
    justify-content: center;
}

.select-business {
    text-align: center;
    font-size: 24px;
    color: #333;
    background-color: #fff;
    padding: 20px;
    border-radius: 10px;
    box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);

    p {
        font-size: 16px;
        color: #666;
        margin-top: 10px;
    }
}

.right-section {
    width: 70%;
    padding: 20px;
    overflow-y: auto;
}

.business-cards {
    display: grid;
    grid-template-columns: repeat(2, 1fr);
    gap: 30px;
    padding: 20px;
}

.business-card {
    background-color: #fff;
    border-radius: 15px;
    overflow: hidden;
    box-shadow: 0 6px 10px rgba(0, 0, 0, 0.1);
    transition: transform 0.3s ease;

    &:hover {
        transform: translateY(-10px);
    }

    img {
        width: 100%;
        height: 250px;
        object-fit: cover;
    }

    p {
        padding: 15px;
        text-align: center;
        font-weight: bold;
        font-size: 18px;
    }
}
</style>