<template>
    <section class="container">
        <div>
            <b-img src="asdf.png" alt="모임 이미지" class="gather-logo" width="100"></b-img>
        </div>
        <article class="titlename">
            <div>
                <h4 class="gathername">강아지를 사랑하는 모임</h4>
            </div>
            <div>
                <h3>모임 이름</h3>
            </div>
            

        </article>
        <article class = "detail-info">
            <b-card title="모임 정보" class="card">
                <b-form class="memberlist">
                    <div>
                        <b-row>
                            <b-col v-for="member in members" :key="member.id" col="3" class="memberimg">
                                <div class="member-item">
                                    <div class="member-role">{{ member.role }}</div>
                                    <b-avatar :src="member.img" :text="member.initial" size="4rem"></b-avatar>
                                    <div class="member-name">{{ member.name }}</div>
                                </div>
                            </b-col>
                        </b-row>
                    </div>
                </b-form>

                <b-form @submit.prevent="handleSubmit">
                    <b-form-group label="모임 내용" label-for="nameInput">
                        <b-form-input
                            id="nameInput"
                            v-model="form.name"
                            required
                            placeholder="모임 내용"
                            readonly
                        ></b-form-input>
                    </b-form-group>
                </b-form>
                <div class="mt-4">
                    <b-form-group label="URL" label-for="urlInput">
                        <b-form-input
                        id="urlInput"
                        v-model="groupUrl"
                        readonly
                        ></b-form-input>
                    </b-form-group>
                </div>


                <div class="mt-4">
                    <b-row>
                        <b-col md="6">
                        <b-form-group label="생성 일자" label-for="createdAtInput">
                            <b-form-input
                            id="createdAtInput"
                            v-model="createdAt"
                            readonly
                            ></b-form-input>
                        </b-form-group>
                        </b-col>
                        <b-col md="6">
                        <b-form-group label="수정 일자" label-for="updatedAtInput">
                            <b-form-input
                            id="updatedAtInput"
                            v-model="updatedAt"
                            readonly
                            ></b-form-input>
                        </b-form-group>
                        </b-col>
                    </b-row>
                </div>

<!-- <b-button type="submit" variant="primary">수정</b-button> -->
            </b-card>
        </article>
    </section>
</template>

<script setup>
    import { ref } from 'vue';

    /* 반응형 변수들 */
    const badgeVisible = ref(true);
    const form = ref({
        name: '',
        email: ''
    });
    const formSubmitted = ref(false);

    const handleSubmit = () => {

        /* 여기서 폼 데이터를 처리 가능 */
        formSubmitted.value = true;
        /* 폼 초기화 */
        form.value.name = '';
        form.value.email = '';
    };

    const members = ref([
        { id: 1, name: '강형욱', role: 'L', img: 'path_to_강형욱_image.jpg', initial: 'ㄱ' },
        { id: 2, name: '오은영', role: 'M', img: 'path_to_오은영_image.jpg', initial: 'ㅇ' },
        { id: 3, name: '김이나', role: 'M', img: 'path_to_김이나_image.jpg', initial: 'ㄱ' },
        { id: 4, name: '이효리', role: 'M', img: 'path_to_이효리_image.jpg', initial: 'ㅇ' }
    ]);
</script>

<style scoped>
    .container {
        overflow: auto;
        height: 100vh;
    }
    .titlename{
        display: flex;
        flex-direction: row-reverse;
        justify-content: center;
        align-items: center;
        gap: 20px;
    }
    .gathername {
        position: relative; 
        display: inline-block;
        padding-bottom: 5px;
    }
    .gathername::after {
        content: ""; 
        position: absolute; 
        left: 0; 
        bottom: 0;
        width: 100%; 
        height: 2px; 
        background: black; 
        margin-top: 5px; 
    }
    .detail-info {
        padding: 2rem;
    }
    .card {
        background-color: #DDF5FF;
    }
    .memberlist{
        display: flex;
        justify-content: space-around;
        align-items: center;
        gap: 20px;
    }
    .member-name {
        display: flex;
        justify-content: center;
        align-items: center;
    }
</style>