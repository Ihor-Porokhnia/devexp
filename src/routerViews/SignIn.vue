<template>
    <div class="mainWrapper">
        <input
                placeholder="Email"
                v-model="email"
        >

        <div class="passwordWrapper">
            <input
                    :type="showPassword ? 'text' : 'password'"
                    placeholder="Пароль"
                    v-model="password"
            >
            <OpenedEye
                    v-if="showPassword"
                    @click="showPassword = !showPassword"
            />
            <ClosedEye
                    v-else
                    @click="showPassword = !showPassword"
            />
        </div>

        <div class="buttonBlock">
<!--            <button-->
<!--                    v-if="!loader && !message"-->
<!--                    :class="{'disabled' : !email || !password || !mac}"-->
<!--                    @click="toDashboard"-->
<!--                    :disabled="!email || !password || !mac"-->
<!--            >-->
<!--                Войти-->
<!--            </button>-->
            <button
                    v-if="!loader && !message"
                    :class="{'disabled' : !email || !password}"
                    @click="signUp"
                    :disabled="!email || !password"
            >
                Зарегистрироваться
            </button>
<!-- ЛОДЕР -->
            <div
                    v-if="loader"
                    class="loader"
            />
<!-- СООБЩЕНИЯ/ОШИБКИ -->
            <div
                    v-if="message"
                    class="message"
            >
                {{message}}
                <div
                        class="close"
                        @click="message = ''"
                />
            </div>
        </div>
    </div>
</template>

<script>
import OpenedEye from '../assets/svg/password-show.svg'
import ClosedEye from '../assets/svg/password-hide.svg'


export default {

    name: 'SignIn',

    components: {
        OpenedEye,
        ClosedEye
    },

    data() {
            return {
                email: 'test@abc.com',
                password: '123qwer',
                message: '',
                loader: false,
                showPassword: false
            }
        },

    methods: {
             signUp() {
                 this.loader = true

                 const encode = window.btoa(this.email + ':'+ this.password)

                 fetch(
                     '/',
                     {
                             method: 'post',
                             body: encode,
                         }
                 ).then(
                     response => {
                             if (response.status !== 404) {
                                 this.loader = false
                                 this.message = 'Регистрация прошла успешно!'
                             }
                             else {
                                 throw response
                             }

                         },
                     error => {
                             this.loader = false
                             this.message = error.message
                         },
                 )
                 .catch(
                     error => {
                         this.loader = false
                         this.message = `${error.statusText}, ${error.status}`
                     }
                 )
            }
        }
    }
</script>

<style scoped lang="sass">
@import '../styles/_loader'


.loader
    @include defaultLoader(28px)
    margin-top: 20px

.mainWrapper
    width: 20%
    margin: 40vh auto auto

.passwordWrapper
    position: relative
    & svg
        position: absolute
        cursor: pointer
        right: 0
        top: 13px


.buttonBlock
    display: flex
    justify-content: center
    margin-top: 30px




input
    width: 100%
    font-size: 15px
    border: none
    padding: 10px
    margin-bottom: 15px
    border-bottom: 2px solid gainsboro
    outline: none



button
    background-color: dodgerblue
    border-radius: 3px
    border: none
    color: white
    box-shadow: 2px 2px 4px grey
    font-size: 15px
    font-weight: 500
    padding: 5px 10px
    outline: none
    cursor: pointer

    &:first-child
        margin-right: 20px

    &:active
        box-shadow: 0 0



.disabled
    color: darkgray
    background-color: steelblue
    box-shadow: 0 0
    cursor: auto





.message
    color: red
    white-space: pre


.close
    display: inline-block
    border-radius: 50%
    width: 10px
    height: 10px
    background-color: red
    box-shadow: gray
    margin-left: 15px
    transition: transform 0.2s ease-out

    &:hover
        transform: scale(1.3)
        cursor: pointer

</style>



