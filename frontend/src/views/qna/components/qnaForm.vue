<template>
    <div class ="container mt-4">
        <b-form inline @submit.prevent="handleSubmit" >
                <b-form-input
                    id="textInput"
                    type="text"
                    v-model="form.text"
                    required
                    placeholder="검색어"
                ></b-form-input>

                <!-- icons -->
            <b-button type="submit" variant="outline-primary" class="form_button">검색</b-button>
        </b-form>
    </div>
</template>

<script setup>
    import { ref } from 'vue';

    const form = ref({
        text: ''
    });

    const handleSubmit = () => {
        /* 폼 데이터 처리 */
        formSubmmited.value = true;

        /* 폼 초기화 */
        form.value.text = '';
    }
</script>

<style scoped>
     .container {
        max-width: 300px;
        display: flex;
        justify-content: flex-end;
      }
    /*
    .form_input {
        margin-right: 8px;
    }
     .form_button {
    } */
</style>