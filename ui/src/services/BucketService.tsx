import axios from "axios";
import {Bucket} from "../model/Bucket";

class BucketService {


    async getBucket(): Promise<Bucket> {
        const response = await axios.get<Bucket>('http://localhost:8080/api/v1/bucket');
        return response.data;
    }

    async saveBucket(bucket: Bucket): Promise<void> {
        await axios.post('http://localhost:8080/api/v1/registration', bucket);
    }


}

export default new BucketService();