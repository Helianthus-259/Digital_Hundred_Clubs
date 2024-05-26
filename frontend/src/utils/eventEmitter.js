class EventEmitter {
    constructor() {
        this.events = {};
    }

    // 订阅事件
    on(event, cbName, callback) {
        if (!this.events[event]) {
            this.events[event] = new Map();
        }
        this.events[event].set(cbName, callback);
    }

    // 触发事件
    emit(event, ...args) {
        if (this.events[event]) {
            for (const callback of this.events[event].values()) {
                callback(...args);
            }
        }
    }

    // 移除事件
    off(event, cbName) {
        if (this.events[event]) {
            this.events[event].delete(cbName);
        }
    }
}

export default new EventEmitter();