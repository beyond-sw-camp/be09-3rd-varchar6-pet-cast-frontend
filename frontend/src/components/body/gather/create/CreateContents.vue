<template>
    <b-form-group label="모임 내용" label-for="contentsInput">
        <b-form-textarea
            id="contentsInput"
            v-model="contents"
            placeholder="모임 내용을 작성하세요."
            rows="3"
            max-rows="6"
            class="contents"
        ></b-form-textarea>
    </b-form-group>
</template>

<script setup>

</script>

<style scoped>
    .contents {
        height: 200px;
    }
    .contents b-form-textarea {
        width: 100%;
        resize: none;
        overflow-y: auto;
    }
</style>