import axios from "axios";
import {Review} from "../model/Review";

class ReviewService {

    async getReviews(): Promise<Array<Review>> {
        const response = await axios.get<Array<Review>>('http://localhost:8080/api/v1/reviews',
            {withCredentials: true});
        return response.data;
    }

    async saveReview(textReview: string) {
        await axios.post('http://localhost:8080/api/v1/reviews/create', textReview,
            {withCredentials: true});
    }
    async deleteReview(id: number): Promise<void> {
        await axios.delete('http://localhost:8080/api/v1/admin/reviews/'+id,{withCredentials: true});
    }


}
export default new ReviewService();