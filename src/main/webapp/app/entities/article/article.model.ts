import { BaseEntity } from './../../shared';

export class Article implements BaseEntity {
    constructor(
        public id?: number,
        public title?: string,
        public content?: string,
    ) {
    }
}
