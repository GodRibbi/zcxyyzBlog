var vm = new Vue({
    el: '#doc-container',
    data: {
        diary:[{}]
    },
    methods:{
        yearToggle: function(e){
            $(e.target).parent('h2').siblings('.timeline-month').slideToggle('slow');
            $(e.target).children('i').toggleClass('fa-caret-down fa-caret-right');
        },
        yearToggle2: function(e){
            $(e.target).parent('a').parent('h2').siblings('.timeline-month').slideToggle('slow');
            $(e.target).toggleClass('fa-caret-down fa-caret-right');
        }
    },
    mounted() {
        axios.get('http://49.232.222.106:3000/blog/DiaryController/getDiarise')
            .then(function (response) {
                vm.diary = response.data;
            })
            .catch(function (error) { // 请求失败处理
                console.log(error);
            });
    }
})
