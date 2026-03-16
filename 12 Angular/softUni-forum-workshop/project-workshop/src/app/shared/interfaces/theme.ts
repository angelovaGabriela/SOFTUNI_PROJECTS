export interface Theme {
    _id: string,
    themeName: string,
    createdAt: string,
    userId: {
        username: string
    },
    subscribers: string[],

}