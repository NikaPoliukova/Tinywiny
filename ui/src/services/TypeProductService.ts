import axios from "axios";
import {TypeProduct} from "../model/TypeProduct";


class TypeProductService {
    async findAllTypes(): Promise<Array<TypeProduct>> {
        const response = await axios.get<Array<TypeProduct>>('http://localhost:8080/api/v1/products/types');
        return response.data;
    }

  }

export default new TypeProductService();