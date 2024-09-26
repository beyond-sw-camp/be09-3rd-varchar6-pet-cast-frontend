<template>
    <div class="container mt-4">
        <b-button class="registerButton" variant="outline-primary" @click="showModal">등록</b-button>

        <b-modal
            id="question_modal"
            title="문의하기"
            v-model="isModalVisible"
            >

            <b-form-textarea
            id="question_title_area"
            v-model="qna_title"
            placeholder="제목"
            rows="1"
             ></b-form-textarea>

            <b-form-textarea
            id="question_area"
            v-model="qna_content"
            placeholder="여기에 입력하세요"
            rows="3"
            max-rows="6"
             ></b-form-textarea>
            <!-- <pre class="mt-3 mb-0">{{ text }}</pre> -->
            <template #modal-footer="{ 등록, 취소 }">
            <b-button variant="secondary" @click="취소">취소</b-button>
            <b-button variant="primary" @click="등록">확인</b-button>
          </template>
        </b-modal>
    </div>

</template>

<script setup>
    import {ref} from 'vue';

    const isModalVisible = ref(false);

    const showModal = () => {
    isModalVisible.value = true;
    }

    // const text = ref('');
    const qna_title = ref('');
    const qna_content = ref('');
    // 전송해준다는 가정

</script>

<style scoped>

</style>