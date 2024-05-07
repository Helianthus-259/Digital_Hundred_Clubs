class EventEmitter {
    constructor() {
        this.events = {};
    }

    // 订阅事件
    on(event, callback) {
        if (!this.events[event]) {
            this.events[event] = new Set();
        }
        this.events[event].add(callback);
    }

    // 触发事件
    emit(event, ...args) {
        if (this.events[event]) {
            this.events[event].forEach(callback => {
                callback(...args);
            });
        }
    }

    // 移除事件
    off(event, callback) {
        if (this.events[event]) {
            this.events[event].delete(callback);
        }
    }
}

export default new EventEmitter();