import { BaseEntity } from './../../shared';

export class SubCategory implements BaseEntity {
    constructor(
        public id?: number,
        public name?: string,
        public alcohol?: boolean,
        public category?: BaseEntity,
        public names?: BaseEntity[],
    ) {
        this.alcohol = false;
    }
}
