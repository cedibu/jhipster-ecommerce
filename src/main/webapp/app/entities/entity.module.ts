import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { EcommerceCategoryModule } from './category/category.module';
import { EcommerceSubCategoryModule } from './sub-category/sub-category.module';
import { EcommerceProductModule } from './product/product.module';
import { EcommerceBrandModule } from './brand/brand.module';
import { EcommerceItemModule } from './item/item.module';
import { EcommerceArticleModule } from './article/article.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    imports: [
        EcommerceCategoryModule,
        EcommerceSubCategoryModule,
        EcommerceProductModule,
        EcommerceBrandModule,
        EcommerceItemModule,
        EcommerceArticleModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class EcommerceEntityModule {}
