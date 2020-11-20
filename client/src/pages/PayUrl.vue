<template>
    <div id="main">
        <div v-if="isCorrect" class="main">
            <img src="@/assets/logo.png" class="space">
            <div>
                상품명 : {{ paymentRequest.goodName }}<br/>
                판매자 : {{ paymentRequest.sellerName }}<br/>
                연락처 : {{ paymentRequest.mobile }}<br/>
                결제금액 : {{ paymentRequest.price }}<br/>
                결제 상태 : <span style="color: red">{{ paymentRequest.paymentState }}</span>
            </div><br/>
            <div class="space">카드결제</div><br/>
            <button v-if="isRequest" class="btn btn-success" @click="payRequest">결제하기</button>
            <button v-if="!isRequest" class="btn btn-danger" @click="payCancel">결제 요청 취소</button>{{' '}}
            <button class="btn btn-outline-dark" @click="goBack">되돌아가기</button>
        </div>

        <div v-else>잘못된 주소입니다.</div>
    </div>
</template>

<script>
import axios from 'axios'

export default {
    name: "PayUrl",
    data() {
        return {
            paymentRequest: {},
            isCorrect: false,
            isRequest: false,
        }
    },
    created() {
        axios
            .get(`http://localhost:8080/p/${this.$route.params.payUrl}`)
            .then(({data}) => {
                if (data !== "") {
                    this.isCorrect = true;
                    this.paymentRequest = data;
                    this.isRequest = (data.paymentState === 'REQUEST');
                } else
                    this.isCorrect = false;
            })
            .catch(() => this.isCorrect = false)
    },
    methods: {
        payRequest() {
            axios
                .post(`http://localhost:8080/p/request`, this.paymentRequest)
                .then(({data}) => {
                    this.isRequest = !this.isRequest;
                    this.paymentRequest = data;
                })
        },
        goBack() {
            this.$router.push('/')
        },
        payCancel() {
            axios
                .post(`http://localhost:8080/oapi/payment/request/cancel`, {
                    memberID: this.paymentRequest.memberID,
                    payUniqueNo: this.paymentRequest.payUniqueNo
                })
                .then(() => {
                    //this.paymentRequest = data;
                })
        }
    }
}
</script>

<style scoped>

#main {
    padding: 3%;
}

.main {
    width: 300px;
    height: 400px;
    text-align: center;
    padding: 1%;
    border: 1px solid dimgray;
    border-radius: 30px;
}

.main > div {
    border: 1px solid lightgray;
    border-radius: 5px;
    width: 100%;
}

.main > :nth-child(2) {
    height: 200px;
    text-align: left;
    padding-top: 40px;
    padding-bottom: 100px;
    padding-left: 10px;
}

.space {
    padding: 3%;
}

</style>
