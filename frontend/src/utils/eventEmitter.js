class EventEmitter {
    constructor() {
        this.events = {};
    }

    // 订阅事件
    on(event, callback) {
        const callbackStr = callback.toString();
        if (!this.events[event]) {
            this.events[event] = new Set();
        }
        let flag = false;
        this.events[event].forEach(callback => {
            if (callback.toString() === callbackStr) {
                flag = true;
            }
        })
        if (!flag) {
            this.events[event].add(callback);
        }
    }

    // 触发事件
    emit(event, ...args) {
        if (this.events[event]) {
            this.events[event].forEach(callback => {
                callback(...args);
            });
        }
    }
}

export default new EventEmitter();