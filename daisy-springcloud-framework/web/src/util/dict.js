import {remote} from '@/api/admin/dict'

export default (app) => {
    app.mixin({
        data() {
            return {
                dicts: {}
            }
        },
        created() {
            let list = this.$options.dicts;
            if (list) {
                let result = [];
                list.forEach(ele => {
                    result.push(new Promise(resolve => {
                        let obj = {}
                        obj[ele] = []
                        remote(ele).then(res => {
                            obj[ele] = res.data.data
                            resolve(obj)
                        }).catch(() => {
                            resolve(obj)
                        })
                    }))
                })
                Promise.all(result).then(res => {
                    res.forEach(ele => {
                        this.dict = Object.assign(this.dicts, ele)
                    })
                })
            }
        }
    })
}
