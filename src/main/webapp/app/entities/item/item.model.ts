import { BaseEntity } from './../../shared';

export class Item implements BaseEntity {
    constructor(
        public id?: number,
        public productId?: number,
        public price?: number,
        public login?: string,
    ) {
    }
}
