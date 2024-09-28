<template>
    <div class="register-form-container">
        
        <!-- 상단 프로필 이미지 -->
        <div class="default-profile" @click="triggerImageUpload">
            <img v-if="profileImage" :src="profileImage" alt="profile-image" class="profile-image" />
            <img v-else src="../../assets/icon/signup/avatar.png" class="default-icon" alt="default" />
            <input 
                id="profile-image" 
                type="file" 
                @change="handleImageUpload" 
                accept="image/*" 
                ref="fileInput" 
                style="display: none;"
            />
        </div>

        <!-- 입력 form -->
        <div class="form-container">    
            <form @submit.prevent="handleSignup">
                <div class="form-group">

                    <div class="label-header">
                        <label for="loginId">아이디*</label>
                        <p v-if="isIdDuplicate" class="error-message">아이디가 중복되었습니다.</p>
                        <p v-else-if="isIdAvailable" class="success-message">사용 가능한 아이디입니다.</p>
                    </div>

                    <div class="input-group">
                        <input
                            type="text"
                            id="loginId"
                            v-model="loginId"
                            placeholder="아이디를 입력하여 주세요."
                            required
                        />                    
                        <button type="button" class="check-btn" @click="checkIdDuplicate">중복체크</button>
                    </div>
                </div>

                <div class="form-group">
                    <label for="password">비밀번호*</label>
                    <input
                        type="password"
                        id="password"
                        v-model="password"
                        placeholder="비밀번호를 입력하여 주세요."
                        required
                    />
                </div>

                <div class="form-group">
                    <div class="label-header">
                        <label for="passwordConfirm">비밀번호 재입력*</label>
                        <p v-if="passwordMismatch" class="password-error-message">비밀번호가 일치하지 않습니다.</p>
                        <p v-else-if="passwordMatch" class="password-success-message">비밀번호가 일치합니다.</p>
                    </div>
                    <input
                        type="password"
                        id="passwordConfirm"
                        v-model="passwordConfirm"
                        @input="checkPasswordMatch"
                        placeholder="비밀번호를 재입력하여 주세요."
                        required
                    />
                </div>

                <div class="form-group">
                    <div class="label-header">
                        <label for="nickname">닉네임*</label>
                        <p v-if="isNicknameDuplicate" class="error-message">닉네임이 중복되었습니다.</p>
                        <p v-else-if="isNicknameAvailable" class="success-message">사용 가능한 닉네임입니다.</p>
                    </div>    

                    <div class="input-group">
                        <input
                            type="text"
                            id="nickname"
                            v-model="nickname"
                            placeholder="사용할 닉네임을 입력하여 주세요."
                            required
                        />

                        <button type="button" class="check-btn" @click="checkNicknameDuplicate">중복체크</button>
                    </div>
                </div>

                <div class="form-group">
                    <label for="userName">이름*</label>
                        <input
                        type="text"
                        id="userName"
                        v-model="userName"
                        placeholder="이름을 입력하여 주세요."
                        required
                    />
                </div>

                <div class="form-group">
                    <label for="phoneNumber">전화번호*</label>
                        <input
                        type="text"
                        id="phoneNumber"
                        v-model="phoneNumber"
                        placeholder="전화번호를 입력하여 주세요."
                        required
                    />
                </div>

                <div class="form-group">
                    <label for="introduction">한 줄 소개</label>
                    <textarea id="introduction" v-model="introduction" placeholder="자기소개를 입력하여 주세요."></textarea>
                </div>

                <div class="form-group">
                    <button type="submit" class="submit-btn">회원가입 완료</button>
                </div>
            </form>
        </div>
        <Modal :isVisible="isModalVisible" :title="modalTitle" :message="modalMessage" @update:isVisible="handleModalClose" />
    </div>
</template>

<script setup>
    import { ref, inject } from 'vue';
    import { useRouter } from 'vue-router';
    import Modal from '../../components/Modal.vue';

    const router = useRouter();

    const profileImage = ref(null);

    const loginId = ref('');
    const password = ref('');
    const userName= ref('');
    const passwordConfirm = ref('');
    const nickname = ref('');
    const phoneNumber = ref('');
    const introduction = ref('');

    const isIdDuplicate = ref(false);
    const isIdAvailable = ref(false);
    const isNicknameDuplicate = ref(false);
    const isNicknameAvailable = ref(false);

    const isModalVisible = ref(false);
    const modalTitle = ref('');
    const modalMessage = ref('');
    
    const jsonIdData = ['user'];
    const jsonNicknameData = ['user01'];

    const passwordMismatch = ref(false);
    const passwordMatch = ref(false);

    function triggerImageUpload() {
        const fileInput = document.getElementById('profile-image');
        fileInput.click();
        }

    function handleImageUpload(event) {
        const file = event.target.files[0];
        if (file) {
            const reader = new FileReader();
            reader.onload = () => {
                profileImage.value = reader.result;
            };
            reader.readAsDataURL(file);
        }
    }

    function checkIdDuplicate() {
        isIdDuplicate.value = false;
        isIdAvailable.value = false;

        if(jsonIdData.includes(loginId.value)){
            isIdDuplicate.value = true;
        } else {
            isIdAvailable.value = true;
        }

        console.log("클릭")
    }

    function checkNicknameDuplicate() {
        isNicknameDuplicate.value = false;
        isNicknameAvailable.value = false;

        if(jsonNicknameData.includes(nickname.value)){
            isNicknameDuplicate.value = true;
        } else {
            isNicknameAvailable.value = true;
        }

    }

    function handleSignup() {

        modalTitle.value = '회원가입 성공';
        modalMessage.value = '회원가입 하신 아이디와 비밀번호로 로그인해주세요.';
        isModalVisible.value = true;

        console.log("Signup data:", {
            loginId: loginId.value,
            password: password.value,
            username: userName.value,
            passwordConfirm: passwordConfirm.value,
            nickname: nickname.value,
            phoneNumber: phoneNumber.value,
            introduction: introduction.value,
            profileImage: profileImage.value,
        });
    }

    const handleModalClose = () => {
        isModalVisible.value = false;
        if (modalTitle.value === '회원가입 성공') {
            router.push(`/login`);
        }
    };

    function checkPasswordMatch() {
        if (passwordConfirm.value === '') {
            passwordMismatch.value = false;
            passwordMatch.value = false;
        } else if (password.value === passwordConfirm.value) {
            passwordMismatch.value = false;
            passwordMatch.value = true;
        } else {
            passwordMismatch.value = true;
            passwordMatch.value = false;
        }
    }
    
</script>

<style scoped>

    .register-form-container{
        justify-content: center;
        align-items: center;    
    }

    .default-profile {
        display: flex;
        position: relative;
        width: 150px;
        height: 150px;
        margin: 20px auto;
        border-radius: 50%;
    }

    .profile-image {
        width: 150px;
        height: 150px;
        border-radius: 50%;
        background-color: #f0f0f0;
        object-fit: cover;
        margin: 0 auto;
        border-radius: 50%;
    }

    .form-container {
        width: 780px;
        height: 820px;
        align-items: center;
        display: flex;
        flex-direction: column;
        margin: 0 auto;
        margin-bottom: 25px;
        background-color: #DDF5FF;
        border-radius: 26px;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    }

    .form-group {
        display: flex;
        width: 500px;
        flex-direction: column;
        margin-top: 20px;
    }

    .form-group label {
        display: block;
        font-weight: bold;
        margin-bottom: 10px;
    }

    .input-group {
        display: flex;
        justify-content: space-between;
        align-items: center; 
    }

    .input-group input {
        flex: 1;
        height: 20px;
        padding: 10px;
        border: 1px solid #ccc;
        border-radius: 15px;
    }

    .check-btn {
        background-color: #b3e5fc;
        border: none;
        border-radius: 5px;
        padding: 10px;
        margin-left: 10px;
        cursor: pointer;
        height: 40px;
    }

    .form-group input,
    .form-group textarea {
        width: 100%;
        height: 20px;
        padding: 10px;
        border: 1px solid #ccc;
        border-radius: 15px;
        font-size: 1rem;
    }

    .form-group textarea {
        height: 80px;
        resize: none;
    }

    .submit-btn {
        background-color: #5ab1ef;
        color: white;
        padding: 10px 20px;
        font-size: 1.2rem;
        border: none;
        border-radius: 10px;
        cursor: pointer;
        width: 100%;
    }

    .label-header{
        display: flex;
    }
    
    .error-message{
        color: red;
        font-size: 12px;
        margin-left: auto; /* 오른쪽 정렬을 위해 추가 */
        margin-right: 70px;
    }
    
    .success-message{
        color: blue;
        font-size: 12px;
        margin-left: auto; /* 오른쪽 정렬을 위해 추가 */
        margin-right: 70px;
    }
    
    .password-error-message{
        color: red;
        font-size: 12px;
        margin-left: auto; /* 오른쪽 정렬을 위해 추가 */
    }
    
    .password-success-message{
        color: blue;
        font-size: 12px;
        margin-left: auto; /* 오른쪽 정렬을 위해 추가 */
    }

    .submit-btn:hover {
        background-color: #4a9edb;
    }

    .label-header {
        display: flex;
        justify-content: space-between;
        align-items: center;
    }
</style>