import api from '@/api'
import {MessagePlugin} from "tdesign-vue-next";

class MyUploadAdapter {
    constructor(loader) {
        // 初始化loader
        this.loader = loader;
    }

    // 实现上传逻辑
    upload() {
        return this.loader.file.then(file => {
            return new Promise((resolve, reject) => {
                // 调用您的axios封装的uploadImage函数
                this._uploadImage(file)
                    .then(response => {
                        if(response.status !== 200){
                            MessagePlugin.warning("服务器出错!")
                            return
                        }
                        response = response.data.status ? response.data : response
                        if (response.status === 200) {
                            // 假设服务器返回了图片URL
                            const imageUrl = response.data.image.url;
                            resolve({
                                default: imageUrl
                            });
                        } else {
                            reject(`Upload failed with status ${response.status}`);
                        }
                    })
                    .catch(error => {
                        reject(`Upload failed: ${error}`);
                    });
            });
        });
    }

    // 可选：如果需要取消上传功能
    abort() {
        // 如果您的axios请求支持取消，请在这里实现取消逻辑
        // 例如，如果有cancelToken
        return Promise.reject();
    }

    // 使用axios封装的uploadImage函数
    _uploadImage(file) {
        // 这里直接调用您的axios封装函数，确保它返回一个Promise
        // 示例参数仅为示意，根据实际情况调整
        console.log(file)
        return api.uploadImage({file:file, flag: 'editor'})
    }
}

export function MyCustomUploadAdapterPlugin(editor) {
    editor.plugins.get("FileRepository").createUploadAdapter = (loader) => {
        return new MyUploadAdapter(loader);
    };
}
