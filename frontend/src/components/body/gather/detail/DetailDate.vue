<template>
    <div class="dated">
        <b-form-group label="생성 일자" label-for="createdAtInput">
            <p id="createdAtInput">{{ formatDate(createdAt) }}</p>
        </b-form-group>

        <b-form-group label="수정 일자" label-for="createdAtInput">
            <p id="createdAtInput">{{ formatDate(updatedAt) }}</p>
        </b-form-group>
    </div>    
</template>

<script setup>
    
    defineProps({
        createdAt:
        {
            type: String,
            required: true
        },
        updatedAt:
        {
            type: String,
            required: true
        }
    });
    const formatDate = (dateString) => {
        const date = new Date(dateString);
        const year = date.getFullYear();
        const month = (date.getMonth() + 1).toString().padStart(2, '0');
        const day = date.getDate().toString().padStart(2, '0');
        return `${year}년 ${month}월 ${day}일`;
    };

    



    
</script>

<style scoped>
    .dated {
        display: flex;
        justify-content: flex-end;
        margin: 10px 0;
        gap:20px;
    }

    .dated h6 {
        margin: 0;
        font-size: 0.9rem;
        color: #333;
    }
</style>